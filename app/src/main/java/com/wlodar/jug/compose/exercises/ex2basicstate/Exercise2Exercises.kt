package com.wlodar.jug.compose.exercises.ex2basicstate

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Exercise2Exercises() {
    Column {
        Text(text = "Exercise1", style = MaterialTheme.typography.h4)
        Divider(thickness = 5.dp)
        Text("solve here")

        Text(text = "Exercise2", style = MaterialTheme.typography.h4)
        Divider(thickness = 5.dp)
        Text("solve here")
    }
}

@Preview(showBackground = true)
@Composable
fun Exercise2ExercisesPreview() {
    Exercise2Exercises()
}