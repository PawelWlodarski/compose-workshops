package com.wlodar.jug.compose.exercises.ex2basicstate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Exercise2Answers() {
    Column {
        Text(text = "Exercise1", style = MaterialTheme.typography.h4)
        Divider(thickness = 5.dp)
        exercise21()

        Text(text = "Exercise2", style = MaterialTheme.typography.h4)
        Divider(thickness = 5.dp)
        exercise22()
    }

}

@Composable
private fun exercise21() {
    var content by remember {
        mutableStateOf("")
    }

    var currentText by remember {
        mutableStateOf("")
    }

    Column {
        Text(content)
        TextField(value = currentText, onValueChange = { currentText = it })
        Button(onClick = {
            content += currentText
            currentText = ""
        }) {
            Text("add text")
        }
    }
}

@Composable
private fun exercise22() {
    @Composable
    fun ListPosition(i: Int) {
        var isClicked by rememberSaveable {
            mutableStateOf(false)
        }

        val chnageState: () -> Unit = {
            isClicked = !isClicked
        }


        if (isClicked)
            Column(
                modifier = Modifier
                    .padding(2.dp)
                    .background(color = Color.Yellow)
                    .wrapContentHeight()
                    .padding(5.dp)
                    .clickable(onClick = chnageState)
            ) {
                Text("Position $i", modifier = Modifier.padding(2.dp))
                Text("click to hide")
            }
        else
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .background(color = Color.LightGray)
                    .wrapContentHeight()
                    .padding(5.dp)
                    .clickable(onClick = chnageState)
            ) {
                Text("click to show")
            }
    }


    LazyColumn {
        items(80) { index ->
            ListPosition(index)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Exercise2AnswersPreview() {
    Exercise2Answers()
}