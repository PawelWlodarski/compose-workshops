package com.wlodar.jug.compose.exercises.ex1basics

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Exercise1Answers() {
    Exercise11Answer()
}

@Composable
fun Exercise11Answer() {
    @Composable
    fun FilledSurface(modifier: Modifier = Modifier, color: Color) {
        Surface(
            color = color,
            modifier = Modifier
                .then(modifier)
                .fillMaxSize(),
            content = {})
    }

    @Composable
    fun ColumnScope.ColumnSurface(color: Color, weight: Float) {
        FilledSurface(Modifier.weight(weight), color)
    }

    @Composable
    fun ColumnScope.RowOfColors(colors: List<Color>, weight: Float) {
        Row(
            Modifier
                .weight(weight)
                .fillMaxSize()
        ) {
            colors.forEach {
                FilledSurface(color = it, modifier = Modifier.weight(1f))
            }
        }
    }

    @Composable
    fun ColumnScope.CenteredContentRow(weight: Float, content: @Composable RowScope.() -> Unit) {
        Row(
            Modifier
                .weight(3f)
                .fillMaxSize(),
            Arrangement.Center,
            Alignment.CenterVertically
        ) {
            content(this)
        }
    }

    Column(
        Modifier.fillMaxSize(),
    ) {
        ColumnSurface(Color.Green, 1f)
        RowOfColors(colors = listOf(Color.Red, Color.Blue, Color.Black), weight = 2f)
        RowOfColors(colors = listOf(Color.Cyan, Color.DarkGray, Color.Magenta, Color.Yellow), weight = 3f)
        CenteredContentRow(weight = 3f) {
            Button(onClick = { /*TODO*/ }) {
                Text("Button")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun Exercise1AnswersPreview() {
    Exercise1Answers()
}
