package com.wlodar.jug.compose.flows

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

val flow = MutableSharedFlow<Int>()

@Composable
fun FlowExperiment1(){

    val scope = rememberCoroutineScope()

    LaunchedEffect(true){
        scope.launch(Dispatchers.IO) {
            (1 .. 50).forEach{
                println("emiting $it")
                flow.emit(it)
                delay(50)
            }
        }
    }

    val flowValue by flow.collectAsState(initial = 0)
    Text("received $flowValue")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FlowExperiment1()
}