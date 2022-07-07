import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun runWithThread() = runBlocking {
    println("시작전 활성화 Thread count = ${Thread.activeCount()}")
    val time = measureTimeMillis {
        val jobs = ArrayList<Thread>()
        repeat(100) {
            jobs += Thread {
                Thread.sleep(1000L)
                //println("${Thread.currentThread().name}")
            }.also { it.start() }
        }
        println("끝 활성화 Thread count = ${Thread.activeCount()}")
        jobs.forEach { it.join() }
    }
    println("걸린 시간 = $time")
}

fun runWithCoroutine() = runBlocking {
    println("시작전 활성화 Thread count = ${Thread.activeCount()}")
    val time = measureTimeMillis {
        val jobs = ArrayList<Job>()
        repeat(100){
            jobs +=launch(Dispatchers.Default){
                delay(1000L)
                //println("${Thread.currentThread().name}")
            }
        }
        println("끝 활성화 Thread count = ${Thread.activeCount()}")
        jobs.forEach { it.join() }
    }
    println("걸린 시간 = $time")
}

fun main(args: Array<String>) {
    println("THREAD")
    runWithThread()
    println("COROUTINE")
    runWithCoroutine()
}