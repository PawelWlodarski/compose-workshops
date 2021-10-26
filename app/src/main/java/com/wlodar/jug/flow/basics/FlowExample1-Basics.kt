package com.wlodar.jug.flow.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun main() {
//    Examples1.example1()
    Examples1.example2()
}

object Examples1{
    fun example1FirstFlow() {
        //no coroutines needed for building flow
        val staticFlow: Flow<Int> = listOf(1, 2, 3).asFlow()

        //only for collect
        runBlocking {
            staticFlow.collect {
                println(it)
            }
        }
    }

    fun example2() {
        val flowBuilder = flow {
            (1..5).forEach {
                emit(it)
                emit(it+100)
                println("------")
                delay(500)
            }
            emit(1000)
        }

        runBlocking {
            flowBuilder.collect(::println)
        }
    }

    fun example3() {
        //CANCELLATION
    }
}

