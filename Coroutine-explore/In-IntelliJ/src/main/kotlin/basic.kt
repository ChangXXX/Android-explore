import kotlinx.coroutines.*

fun usingBlock() = runBlocking {
    launch { // 코루틴을 새로 launch 하고 사용
        delay(1000L) // non-blocking 1초 딜레이
        println("Block!") // 1초 딜레이 후 프린트
    }
    println("Hello") // 메인 코루틴은 이전 코루틴이 딜레이되는 동안 지속됨
}

fun nonBlock() {
    GlobalScope.launch { // 백그라운드에서 작업하는 새로운 코루틴
        delay(1000L) // non-blocking 1초 딜레이
        println("NonBlock!") // 딜레이 후 프린트
    }
    println("Hello,") // 메인 코루틴은 이전 코루틴이 딜레이되는 동안 지속됨
    Thread.sleep(2000L) // 현재 스레드를 2초간 블록해 다른 작업을 기다림
}

fun usingGlobalScope() = runBlocking {
    GlobalScope.launch {
        delay(1000L)
        println("GlobalBlock!")
    }
    println("Hello,")
    delay(2000L) // delay 지울 시 GlobalBlock! 출력 안됨
}

fun usingGlobalScopeWithJob() = runBlocking {
    val job = GlobalScope.launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join()
}

fun usingCoroutineScope() = runBlocking {
    launch {
        delay(1000L)
        println("CoroutineBlock!")
    }
    println("Hello,")
}

fun testScope() = runBlocking {
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope")
    }
    println("Coroutine scope is over")
}

fun testScope2() = runBlocking {
    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope")
    }

    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    println("Coroutine scope is over")
}

suspend fun doWorld() {
    delay(1000L)
    println("World!")
}

fun test() = runBlocking {
    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope")
    }

    val job = GlobalScope.launch {
        delay(200L)
        println("Task from runBlocking")
    }

    println("Coroutine scope is over")
    job.join()
}

fun main() {
    test()
}