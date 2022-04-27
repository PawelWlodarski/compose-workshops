package com.wlodar.jug.compose.exercises.ex3themes

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wlodar.jug.compose.ui.theme.ComposeWorkshopsTheme
import kotlinx.coroutines.launch

@Composable
fun Exercise3Demo() {
//    justText()
//    materialTheme()
//    shapes()
    scaffold()

}

@Composable
fun scaffold() {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val topBar: @Composable () -> Unit = {
        TopAppBar {
            Text("Text on Top")
            Button(onClick = { }) {
                Text("Button next to it")
            }
        }
    }

    val bottomBar: @Composable () -> Unit = {
        BottomAppBar(
            cutoutShape = MaterialTheme.shapes.small.copy(
                CornerSize(percent = 50)
            )
        ) {
            Text("Text on Bottom")
        }
    }

    val floatingActionButton: @Composable () -> Unit = {
        val onClick: () -> Unit = {
            scope.launch {
                scaffoldState.snackbarHostState
                    .showSnackbar(
                        "Snackbar",
                        actionLabel = "Action",
                        duration = SnackbarDuration.Indefinite
                    )
            }
        }


        FloatingActionButton(onClick = onClick) {
            Icon(Icons.Rounded.Add, contentDescription = "Localized description")
        }
    }


    val drawer: @Composable (ColumnScope.() -> Unit) = {
        Text("Drawer title", modifier = Modifier.padding(16.dp))
        Divider()
    }



    Scaffold(
        drawerContent = drawer,
        scaffoldState = scaffoldState,
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) { contentPadding ->
        Text("Content inside scaffold")
    }
}

@Composable
fun justText() {
    ComposeWorkshopsTheme {
        Text("just text")
    }
}

@Composable
fun materialTheme() {
    Column {
        Text(text = "sample1", style = MaterialTheme.typography.h3)
        Text(
            text = "sample1",
            style = MaterialTheme.typography.h3.copy(color = Color.Red)
        )
    }
}

@Composable
fun shapes() {
    Column {
        Button(
            shape = MaterialTheme.shapes.small,
            onClick = {}
        ) {
            Text("small shape button")
        }

        Button(
            shape = MaterialTheme.shapes.medium,
            onClick = {}
        ) {
            Text("medium shape button")
        }

        Button(
            shape = MaterialTheme.shapes.large,
            onClick = {}
        ) {
            Text("large shape button")
        }

        TextField(
            value = "filled text field", onValueChange = {},
            shape = MaterialTheme.shapes.small.copy(
                bottomStart = ZeroCornerSize, // overrides small theme style
                bottomEnd = ZeroCornerSize // overrides small theme style
            ),
            modifier = Modifier.width(100.dp)
        )

        Icon(Icons.Rounded.Menu, contentDescription = "Localized description")
    }

}

@Preview(
    showBackground = true,
    name = "light mode",
    showSystemUi = true
)
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun Exercise3DemoPreview() {
    Exercise3Demo()
}