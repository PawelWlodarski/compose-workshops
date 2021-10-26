package com.wlodar.jug.flow.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
  listOf(1,2,3).filter { it > 2 }
}


object Coroutines1Basics{

    fun example1(){
        val ourFirstScope = CoroutineScope(Dispatchers.Default)

        runBlocking {
            val job = ourFirstScope.launch {
                println("inside coroutine")
            }

            job.join()
            println("end")
        }
    }

}