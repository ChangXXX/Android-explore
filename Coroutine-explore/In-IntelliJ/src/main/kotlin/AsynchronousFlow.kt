import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis

fun simple(): List<Int> = listOf(1, 2, 3)

fun simpleSequence(): Sequence<Int> = sequence { // sequence builder
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it
        yield(i) // yield next value
    }
}

suspend fun suspendSimple(): List<Int> {
    delay(100)
    return listOf(1, 2, 3)
}

fun flowSimple(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something
        emit(i) // emit next value
    }
}

fun coldSimple(): Flow<Int> = flow {
    println("Flow started")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun printSimples() = runBlocking {
    println("Calling simple function...")
    val flow = coldSimple()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}

fun cancelSimple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

fun flowCancel() = runBlocking {
    withTimeoutOrNull(250) { // Timeout after 250ms
        cancelSimple().collect { value -> println(value) }
    }
    println("Done")
}

suspend fun performRequest(request: Int): String {
    delay(1000) // imitate long-running asynchronous work
    return "response $request"
}

fun mappingFlow() = runBlocking {
    (1..3).asFlow() // a flow of requests
        .map { request -> performRequest(request) }
        .collect { response -> println(response) }
}

fun transformOperator() = runBlocking {
    (1..3).asFlow() // a flow of requests
        .transform { request ->
            emit("Making request $request")
            emit(performRequest(request))
        }
        .collect { response -> println(response) }
}

fun numbers(): Flow<Int> = flow {
    try {
        emit(1)
        emit(2)
        println("This line will not execute")
        emit(3)
    } finally {
        println("Finally in numbers")
    }
}

fun takeNumbers() = runBlocking {
    numbers()
        .take(2) // take only the first two
        .collect { value -> println(value) }
}

fun reduceFlow() = runBlocking {
    val sum = (1..5).asFlow()
        .map { it * it } // squares of numbers from 1 to 5
        .reduce { a, b -> a + b } // sum them (terminal operator)
    println(sum)
}

fun sequentialFlow() = runBlocking {
    (1..5).asFlow()
        .filter {
            println("Filter $it")
            it % 2 == 0
        }
        .map {
            println("Map $it")
            "string $it"
        }.collect {
            println("Collect $it")
        }
}

fun contextSimple(): Flow<Int> = flow {
    log("Started simple flow")
    for (i in 1..3) {
        emit(i)
    }
}

fun contextFlow() = runBlocking<Unit> {
    contextSimple().collect { value -> log("Collected $value") }
}

fun wrongSimple(): Flow<Int> = flow {
    // The WRONG way to change context for CPU-consuming code in flow builder
    kotlinx.coroutines.withContext(Dispatchers.Default) {
        for (i in 1..3) {
            Thread.sleep(100) // pretend we are computing it in CPU-consuming way
            emit(i) // emit next value
        }
    }
}

fun wrongChangeContext() = runBlocking {
    wrongSimple().collect { value -> println(value) }
}

fun flowOnSimple(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(100) // pretend we are computing it in CPU-consuming way
        log("Emitting $i")
        emit(i) // emit next value
    }
}.flowOn(Dispatchers.Default) // RIGHT way to change context for CPU-consuming code in flow builder

fun rightChangeContext() = runBlocking {
    flowOnSimple().collect { value ->
        log("Collected $value")
    }
}

fun bufferingSimple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // pretend we are asynchronously waiting 100 ms
        emit(i) // emit next value
    }
}

fun runBuffering() = runBlocking<Unit> {
//    val time = measureTimeMillis {
//        bufferingSimple().collect { value ->
//            delay(300) // pretend we are processing it for 300 ms
//            println(value)
//        }
//    }
//    println("Collected in $time ms")
    val time = measureTimeMillis {
        bufferingSimple()
            .buffer() // buffer emissions, don't wait
            .collect { value ->
                delay(300) // pretend we are processing it for 300 ms
                println(value)
            }
    }
    println("Collected in $time ms")
}

fun conflation() = runBlocking {
    val time = measureTimeMillis {
        bufferingSimple()
            .conflate() // conflate emissions, don't process each one
            .collect { value ->
                delay(300) // pretend we are processing it for 300 ms
                println(value)
            }
    }
    println("Collected in $time ms")
}

fun xxxLatest() = runBlocking {
    val time = measureTimeMillis {
        bufferingSimple()
            .collectLatest { value -> // cancel & restart on the latest value
                println("Collecting $value")
                delay(300) // pretend we are processing it for 300 ms
                println("Done $value")
            }
    }
    println("Collected in $time ms")
}

fun zipFlows() = runBlocking {
    val nums = (1..3).asFlow() // numbers 1..3
    val strs = flowOf("one", "two", "three") // strings
    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string
        .collect { println(it) } // collect and print
}

fun nonCombine() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
    val startTime = System.currentTimeMillis() // remember the start time
    nums.zip(strs) { a, b -> "$a -> $b" } // compose a single string with "zip"
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

fun combine() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) } // numbers 1..3 every 300 ms
    val strs = flowOf("one", "two", "three").onEach { delay(400) } // strings every 400 ms
    val startTime = System.currentTimeMillis() // remember the start time
    nums.combine(strs) { a, b -> "$a -> $b" } // compose a single string with "combine"
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500) // wait 500 ms
    emit("$i: Second")
}

fun testFlatten() = runBlocking {
    val a = (1..3).asFlow().map { requestFlow(it) }
}

fun flatConcat() = runBlocking {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapConcat { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

fun flatMerge() = runBlocking {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapMerge { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

fun flatLatest() = runBlocking {
    val startTime = System.currentTimeMillis() // remember the start time
    (1..3).asFlow().onEach { delay(100) } // a number every 100 ms
        .flatMapLatest { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

fun emitOneToThree(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i) // emit next value
    }
}

fun collectorTryAndCatch() = runBlocking {
    try {
        emitOneToThree().collect { value ->
            println(value)
            check(value <= 1) { "Collected $value" }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}

fun emitOneToThreeString(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i) // emit next value
        }
    }
        .map { value ->
            check(value <= 1) { "Crashed on $value" }
            "string $value"
        }

fun everythingIsCaught() = runBlocking {
    try {
        emitOneToThreeString().collect { value -> println(value) }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}

fun transparent() = runBlocking {
    emitOneToThreeString()
        .catch { e -> emit("Caught $e") } // emit on exception
        .collect { value -> println(value) }
}

fun transparentCatch() = runBlocking {
    emitOneToThree()
        .catch { e -> println("Caught $e") } // does not catch downstream exceptions
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}

fun catchingDeclaratively() = runBlocking {
    emitOneToThree()
        .onEach { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
        .catch { e -> println("Caught $e") }
        .collect()
}

fun imperativeFinally() = runBlocking {
    try {
        emitOneToThree().collect { value -> println(value) }
    } finally {
        println("Done")
    }
}

fun declarativeHandling() = runBlocking {
    emitOneToThree()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }
}

fun throwSimple(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun declarativeHandlingError() = runBlocking {
    throwSimple()
        .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally") }
        .catch { cause -> println("Caught exception") }
        .collect { value -> println(value) }
}

fun normSimple(): Flow<Int> = (1..3).asFlow()

fun successfulCompletion() = runBlocking {
    normSimple()
        .onCompletion { cause -> println("Flow completed with $cause") }
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}

fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun collectingFlow() = runBlocking {
    events()
        .onEach { event -> println("Event: $event") }
        .collect()
    println("Done")
}

fun launchInFlow() = runBlocking {
    events()
        .onEach { event -> println("Event: $event") }
        .launchIn(this) // <--- Launching the flow in a separate coroutine
    println("Done")
}

fun foo(): Flow<Int> = flow {
    for (i in 1..5) {
        println("Emitting $i")
        emit(i)
    }
}

fun flowCancellationCheck() = runBlocking {
    foo().collect { value ->
        if (value == 3) cancel()
        println(value)
    }
}

fun main() {
    flowCancellationCheck()
}
