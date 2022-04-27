package com.wlodar.jug.compose.exercises.ex3themes

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wlodar.jug.compose.Destinations
import com.wlodar.jug.compose.ui.infrastructure.NavigatingButton
import com.wlodar.jug.compose.ui.infrastructure.WorkshopPageLayout


@Composable
fun Exercise3Themes(navController: NavHostController) {
    WorkshopPageLayout {
        Column {
            NavigatingButton(navController, Destinations.EXERCISE3_DEMO, "demo")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    Exercise3Themes(navController)
}