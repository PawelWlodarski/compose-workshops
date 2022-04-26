package com.wlodar.jug.compose.exercises.ex1basics

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun Exercise1Exercises() {
    Exercise11()
}

@Composable
fun Exercise11() {
    Text(
        text = """
        GreenBAr - weight 1f
        Red,BluBlack -Row - weight 2f
        4 colors Row - weight 3f
        White space with centered button - weight 3f
        """
    )


}

@Preview(showBackground = true)
@Composable
fun Exercise1ExercisesPreview() {
    Exercise1Exercises()
}
