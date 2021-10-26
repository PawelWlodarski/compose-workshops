package com.wlodar.jug.flow.basics

import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

fun main() {
    Examples10.attempt1()
}


object Examples10{
    fun attempt1(){
        /**
         * Flow provides channel for internal callback listener
         */
        val flow=callbackFlow<String> {

            val listener=object : Listener{
                override fun listen(msg: String) {
//                    channel
//                        .trySendBlocking("dostalem element $msg")
//                        .onFailure(::println)

                    runBlocking {  ///COMMENT OUT
                        channel.send("dostalem element $msg")
                    }
                }
            }

            API.register(listener)

            awaitClose{   ///COMMENT OUT
                API.clear()
            }

            //            API.onCompleted() = channel.close()
        }

        runBlocking {

            launch {
                delay(300) ///COMMENT OUT
                API.send("message 1 from old API")
                delay(300)
                API.send("message 2 from old API")
            }

            val flowJob=launch {
                flow.collect(::println)
            }
            delay(1000)
            flowJob.cancel()

        }

    }


    interface Listener{
        fun listen(msg:String)
    }

    object API{
        private var listeners = emptyList<Listener>()

        fun register(l:Listener){
            listeners = listeners + l
        }

        fun send(msg:String){
            listeners.forEach{it.listen(msg)}
        }

        fun clear(){
            println("api cleared")
            listeners
        }

    }
}