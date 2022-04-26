package com.wlodar.jug.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wlodar.jug.compose.exercises.ex1basics.Exercise1Answers
import com.wlodar.jug.compose.exercises.ex1basics.Exercise1Basics
import com.wlodar.jug.compose.exercises.ex1basics.Exercise1Demo
import com.wlodar.jug.compose.exercises.ex1basics.Exercise1Exercises
import com.wlodar.jug.compose.ui.theme.ComposeWorkshopsTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeWorkshopsTheme(darkTheme = false) {
                ComposeWorkshops()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        ComposeWorkshops()
    }
}

@Composable
fun ComposeWorkshops() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "start") {
        setMainNavigation(navController)
        setExercise1Navigation(navController)
    }
}

private fun NavGraphBuilder.setMainNavigation(navController: NavHostController) {
    composable(Destinations.START) { StartPage(navController) }
}

private fun NavGraphBuilder.setExercise1Navigation(navController: NavHostController) {
    composable(Destinations.EXERCISE1) { Exercise1Basics(navController) }
    composable(Destinations.EXERCISE1_DEMO) { Exercise1Demo() }
    composable(Destinations.EXERCISE1_EXERCISES) { Exercise1Exercises() }
    composable(Destinations.EXERCISE1_ANSWERS) { Exercise1Answers() }
}

@Composable
fun StartPage(navController: NavHostController) {

    WorkshopPageLayout {
        Column(modifier = Modifier.padding(5.dp)) {
            Button(onClick = { navController.navigate("exercise1") }) {
                Text(text = "Exercise1 - Intro")
            }
        }
    }
}

@Composable
fun WorkshopPageLayout(content: @Composable () -> Unit) {
    @Composable
    fun WorkshopsAppBar(): @Composable () -> Unit = { TopAppBar(title = { Text("Compose Workshops") }) }

    Scaffold(
        topBar = WorkshopsAppBar()
    ) {
        content()
    }
}

