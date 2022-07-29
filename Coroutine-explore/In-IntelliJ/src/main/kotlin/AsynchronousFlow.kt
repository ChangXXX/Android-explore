import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

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

fun mappingFlow() = runBlocking{
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

fun takeNumbers() = runBlocking{
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

fun contextSimple(): Flow<Int> = flow{
    log("Started simple flow")
    for (i in 1..3) {
        emit(i)
    }
}

fun contextFlow() = runBlocking<Unit> {
    contextSimple().collect { value -> log("Collected $value") }
}

fun main(args: Array<String>) {
    contextFlow()
}
