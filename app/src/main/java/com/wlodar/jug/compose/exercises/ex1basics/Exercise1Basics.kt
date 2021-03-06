package com.wlodar.jug.compose.exercises.ex1basics

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wlodar.jug.compose.Destinations
import com.wlodar.jug.compose.ui.infrastructure.NavigatingButton
import com.wlodar.jug.compose.ui.infrastructure.WorkshopPageLayout


@Composable
fun Exercise1Basics(navController: NavHostController) {
    WorkshopPageLayout {
        Column {
            NavigatingButton(navController, Destinations.EXERCISE1_DEMO, "demo")
            NavigatingButton(navController, Destinations.EXERCISE1_EXERCISES, "exercise")
            NavigatingButton(navController, Destinations.EXERCISE1_ANSWERS, "answer")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Exercise1Basics(navController)
}