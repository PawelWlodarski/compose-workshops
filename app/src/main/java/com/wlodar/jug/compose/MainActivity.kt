package com.wlodar.jug.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wlodar.jug.compose.flows.FlowExperiment1
import com.wlodar.jug.compose.ui.theme.ComposeWorkshopsTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {

    private val ourState by viewModels<OurState>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            application(ourState)
            FlowExperiment1()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        application(ourState)
    }
}

@Composable
fun CustomComponent(name: String) {
    Text(text = "Hello $name!")
}

class OurState(val init:Int=0):ViewModel(){
    private val state= MutableLiveData(init)

    val value:LiveData<Int>
        get() = state

    fun updateEvent(newValue:Int){
        state.value=newValue
    }
}

@Composable
private fun application(state:OurState) {
    ComposeWorkshopsTheme {
        Column( modifier = Modifier.border(BorderStroke(2.dp, Color.Red))) {
            CustomComponent("SomethingElse")
            CustomComponent("bbb")
            HelloButton(state)
        }
    }
}

@Composable
private fun HelloButton(state:OurState) {
    val value by state.value.observeAsState(state.init)
    val scope = rememberCoroutineScope()

    println("recomposing hello button")
    if(value % 3 ==0)  {
        Text(text = "can divide by 3")
    }

    val onClickProcedure: () -> Unit = {
        scope.launch(Dispatchers.IO) {
            try{
                withContext(Dispatchers.Main){
                    state.updateEvent(value + 1)
                }
                delay(2000)
                println("*************Coroutine ended**************")
            }catch (e:CancellationException){
                println("*************Coroutine cancelled**************")
            }
        }
    }

    Button(onClick = onClickProcedure){
        WhiteText("clicked $value times")
    }
}

@Composable
private fun WhiteText(text: String) {
    Text(text, color = Color.White)
}