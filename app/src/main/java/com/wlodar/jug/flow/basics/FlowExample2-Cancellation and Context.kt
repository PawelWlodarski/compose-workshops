package com.wlodar.jug.flow.basics

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun main() {
//        Examples2.example1CancelScope()
//    Examples2.example2ChangeContextIncorrect()
    Examples2.example2ChangeContextCorrect()
}

object Examples2 {
    fun example1CancelScope() = runBlocking {
        val flow = (1..10).toList().asFlow().transform {
            emit(it)
            delay(600)
        }

        launch {
            flow.collect {     //Collect returns unit and it is sequential!

                println(it)
            }
        }
        launch {
            flow.collect {     //Collect returns unit and it is sequential!
                println(it)
            }
        }
        val jobContext = this
        delay(3000)
        jobContext.cancel()

    }

    fun example2ChangeContextIncorrect() = runBlocking {
        val flow = flow {
            (1..10).forEach {
                withContext(Dispatchers.IO) {
                    delay(500)
                    emit(it)
                }
            }
        }

        flow.transform {
            val element = it
            withContext(Dispatchers.Main) {
                emit(element)
            }
        }

        flow.collect {
            println("collected $it on thread : ${Thread.currentThread()}")
        }
    }

    fun example2ChangeContextCorrect() = runBlocking {
        val flow = flow {
            (1..10).forEach {
                delay(500)
                println("emitted $it on thread : ${Thread.currentThread().name}")
                emit(it)
            }
        }.flowOn(Dispatchers.IO)

        val transformed = flow
            .transform {
                val element = it
                println("transformed $it on thread : ${Thread.currentThread().name}")
                emit(element)

            }//.flowOn(Dispatchers.Main)


        transformed
            .collect {
                println("collected $it on thread : ${Thread.currentThread().name}")
            }
    }
}