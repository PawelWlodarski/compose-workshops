package com.wlodar.jug.compose.exercises.ex1basics

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun Exercise1Basics(navController: NavHostController) {
    Column {
        Button(onClick = {navController.navigate("exercise1Demo"    )}) {
            Text("demo")
        }
        Button(onClick = {}) {
            Text("exercise")
        }
        Button(onClick = {}) {
            Text("answer")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Exercise1Basics(navController)
}