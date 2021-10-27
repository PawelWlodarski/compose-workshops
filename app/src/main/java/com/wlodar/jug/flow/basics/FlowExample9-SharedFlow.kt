package com.wlodar.jug.flow.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    Examples9.example1QueueBetweenCoroutines()
}


object Examples9{
    fun example1QueueBetweenCoroutines(){
        val flow = MutableSharedFlow<Int>()

        runBlocking {
            launch {
                (1 .. 10).forEach{
                    flow.emit(it)
                    delay(500)
                }
            }

            launch {
                delay(1000)
                flow.collect{
                    println("received $it")
                }
            }
        }
    }
}