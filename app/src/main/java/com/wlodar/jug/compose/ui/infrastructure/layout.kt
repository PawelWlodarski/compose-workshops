package com.wlodar.jug.compose.ui.infrastructure

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WorkshopPageLayout(content: @Composable () -> Unit) {
    @Composable
    fun WorkshopsAppBar(): @Composable () -> Unit = { TopAppBar(title = { Text("Compose Workshops") }) }

    Scaffold(
        topBar = WorkshopsAppBar()
    ) {
        Box(modifier = Modifier.padding(5.dp)) {
            content()
        }
    }
}