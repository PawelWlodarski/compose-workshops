package com.wlodar.jug.flow.basics

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
        Examples2.example1CancelScope()
}

object Examples2{
    fun example1CancelScope()=runBlocking{
        val flow = (1 .. 10).toList().asFlow().transform {
            emit(it)
            delay(600)
        }

        launch {
            flow.collect{     //Collect returns unit and it is sequential!

                println(it)
            }
        }
        launch {
            flow.collect{     //Collect returns unit and it is sequential!
                println(it)
            }
        }
        val jobContext = this
        delay(3000)
        jobContext.cancel()

    }
}