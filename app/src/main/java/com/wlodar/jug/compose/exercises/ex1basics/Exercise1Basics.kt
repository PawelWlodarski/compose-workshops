package com.wlodar.jug.compose.exercises.ex1basics

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wlodar.jug.compose.Destinations


@Composable
fun Exercise1Basics(navController: NavHostController) {
    Column {
        Button(onClick = { navController.navigate(Destinations.EXERCISE1_DEMO) }) {
            Text("demo")
        }
        Button(onClick = { navController.navigate(Destinations.EXERCISE1_EXERCISES) }) {
            Text("exercise")
        }
        Button(onClick = { navController.navigate(Destinations.EXERCISE1_ANSWERS) }) {
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