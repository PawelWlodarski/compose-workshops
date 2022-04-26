package com.wlodar.jug.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wlodar.jug.compose.exercises.ex1basics.Exercise1Basics
import com.wlodar.jug.compose.exercises.ex1basics.Exercise1Demo
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
        composable("start") { StartPage(navController) }
        composable("exercise1") { Exercise1Basics(navController) }
        composable("exercise1Demo") { Exercise1Demo() }
    }
}

@Composable
fun StartPage(navController: NavHostController) {

    Column(modifier = Modifier.padding(5.dp)) {
        Text(text = "Compose Workshops", style = MaterialTheme.typography.h4)
        Button(onClick = { navController.navigate("exercise1") }) {
            Text(text = "Exercise1 - Intro")
        }
    }

}

