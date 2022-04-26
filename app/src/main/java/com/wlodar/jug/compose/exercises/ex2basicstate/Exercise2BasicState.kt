package com.wlodar.jug.compose.exercises.ex2basicstate

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wlodar.jug.compose.Destinations
import com.wlodar.jug.compose.ui.infrastructure.NavigatingButton
import com.wlodar.jug.compose.ui.infrastructure.WorkshopPageLayout

@Composable
fun Exercise2Basicstate(navController: NavHostController) {
    WorkshopPageLayout {
        Column {
            NavigatingButton(navController, Destinations.EXERCISE2_DEMO, "demo")
            NavigatingButton(navController, Destinations.EXERCISE2_EXERCISES, "exercises")
            NavigatingButton(navController, Destinations.EXERCISE2_ANSWERS, "answers")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Exercise2Basicstate(navController)
}