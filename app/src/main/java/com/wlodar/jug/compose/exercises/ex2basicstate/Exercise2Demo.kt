package com.wlodar.jug.compose.exercises.ex2basicstate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

@Composable
fun Exercise2Demo() {
    defineStateInCompose()
//    whyRemember()
//    saveAndRemember()
//    savingObjects()
//    uiState()
//    ViewModelExample.modelState()
}

private object ViewModelExample {
    class Model : ViewModel() {
        private val data = MutableLiveData(0)

        fun readData(): LiveData<Int> = data

        fun changeData(newValue: Int) {
            data.value = newValue
        }
    }

    val model = Model()

    @Composable
    fun modelState() {

        //show that remember is not working
        val dataState by model.readData().observeAsState()

        Column {
            Text("Current Data Value $dataState")
            Button(onClick = { model.changeData(7) }) {
                Text(text = "changeData")
            }
        }


    }
}


@Composable
private fun defineStateInCompose() {
    @Composable
    fun sample1() {
        var state = 0

        Column {
            Text("current value is : $state")
            Button(onClick = { state += 1 }) {
                Text("add ")
            }
        }
    }

    @Composable
    fun sample2() {
//        var state: MutableState<Int> = mutableStateOf(0)  //show error
        val state: MutableState<Int> = remember { mutableStateOf(0) }

        Column {
            Text("current value is : ${state.value}")
            Button(onClick = { state.value = state.value + 1 }) {
                Text("add ")
            }
        }
    }

    @Composable
    fun sample3() {
        //those two imports are critical for delegation
        //import androidx.compose.runtime.getValue
        //import androidx.compose.runtime.setValue
        var state by remember { mutableStateOf(0) }

        Column {
            Text("current value is : $state")
            Button(onClick = { state += 1 }) {
                Text("add ")
            }
        }
    }

    @Composable
    fun sample4() {
        //those two imports are critical for delegation
        //import androidx.compose.runtime.getValue
        //import androidx.compose.runtime.setValue
        val (state: Int, setState: (Int) -> Unit) = remember { mutableStateOf(0) }

        Column {
            Text("current value is : $state")
            Button(onClick = { setState(state + 1) }) {
                Text("add ")
            }
        }
    }

    sample1()
//    sample2()
//    sample3()
//    sample4()

}

@Composable
private fun whyRemember() {
    Column {
//        println("init") //addint this trigger composition?
        var state by remember { mutableStateOf(0) }
        var plainState = 0
        val onClick = {
            state += 1
            plainState += 1
        }
        if (state % 5 == 0) Text("current value is divisible by 5")
        println("RECOMPOSITION")
        Text("current value is : $state and plainState=$plainState")


        Button(onClick = onClick) {
            Text("add ")
        }
    }
}

@Composable
private fun saveAndRemember() {
    @Composable
    fun sample1() {
        var state by remember { mutableStateOf(0) }

        Column {
            Text("current value is : $state")
            Button(onClick = { state += 1 }) {
                Text("add ")
            }
        }
    }

    @Composable
    fun sample2() {
        var state by rememberSaveable { mutableStateOf(0) }

        Column {
            Text("current value is : $state")
            Button(onClick = { state += 1 }) {
                Text("add ")
            }
        }
    }

    //rotate
    sample1()
//    sample2()
}

@Composable
private fun savingObjects() {
    class User(val name: String, val email: String, val age: Int)

    @Composable
    fun sample1() {
        var user by rememberSaveable {
            mutableStateOf(User("UserName", "UserMail", 30))
        }

        Text("user : ${user.name}, age ${user.age} has email : ${user.email}")
    }

    @Composable
    fun sample2() {
        val userSaver = listSaver<User, Any>(
            save = { listOf(it.name, it.email, it.age) },
            restore = { User(it[0] as String, it[1] as String, it[2] as Int) }
        )

        //show second implementation of rememberSaveable
        var user by rememberSaveable(stateSaver = userSaver) {
            mutableStateOf(User("UserName", "UserMail", 30))
        }

        Text("user : ${user.name}, age ${user.age} has email : ${user.email}")
    }

//    sample1()
//    sample2()

}

@Composable
private fun uiState() {
    @Composable
    fun sample1() {
        var isClicked by remember {
            mutableStateOf(false)
        }

        val boxHeight: Dp = if (isClicked) 200.dp else 50.dp
        val label = if (isClicked) "click to show less" else "click to show more"

        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, Color.Red))
                .padding(1.dp)
                .height(boxHeight)
                .fillMaxWidth()
                .clickable { isClicked = !isClicked }
        ) {
            Text(label)
        }
    }

    @Composable
    fun sample2() {
        val elements = (1..200).map { it.toString() }
        val listState = rememberLazyListState()


        Row {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                //another special import
                //import androidx.compose.foundation.lazy.items
                items(elements) { element ->
                    Text(text = element, fontSize = 24.sp)
                }
            }
            Text("First visible list item is ${listState.firstVisibleItemIndex}")
        }


    }

    sample1()
//    sample2()
}

@Preview(showBackground = true)
@Composable
fun Exercise2DemoPreview() {
    Exercise2Demo()
}