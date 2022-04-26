package com.wlodar.jug.compose.ui.infrastructure

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun NavigatingButton(navController: NavController, dest: String, label: String) {
    Button(onClick = { navController.navigate(dest) }, modifier = Modifier.fillMaxWidth()) {
        Text(text = label)
    }
}