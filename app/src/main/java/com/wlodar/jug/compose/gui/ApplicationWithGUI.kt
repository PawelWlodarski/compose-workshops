package com.wlodar.jug.compose.gui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wlodar.jug.compose.gui.data.DI
import com.wlodar.jug.compose.gui.data.User
import com.wlodar.jug.compose.gui.data.UsersRepository
import com.wlodar.jug.compose.gui.ui.theme.ComposeWorkshopsTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.launch
import java.lang.RuntimeException


class ApplicationWithGUI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SomeGuiApplication()
        }
    }
}

@Composable
fun SomeGuiApplication() {
    val fap = @Composable {
        FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = { /* ... */ }) {
            Icon(Icons.Filled.AccountBox, contentDescription = null)
        }
    }

    ComposeWorkshopsTheme {
        Scaffold(
                topBar = { topBar() }, // waith for kotlin 1.6
                floatingActionButton = fap
        ) {
            AppBody()
        }
    }
}

@Composable
fun topBar() {
    val appTitle = @Composable {
        Text(
                text = "Some AppTitle",
                style = MaterialTheme.typography.h3
        )
    }

    val topBarActions: @Composable RowScope.() -> Unit = {
        IconButton(onClick = { /* doSomething() */ }) {
            Icon(Icons.Filled.Email, contentDescription = null)
        }
    }

    TopAppBar(
            actions = topBarActions,
            title = appTitle
    )
}


@Composable
fun AppBody() {
    Surface(
            color = MaterialTheme.colors.secondary
    ) {
        val usersPageState by rememberSaveable(stateSaver = UsersPageState.saver) {
            mutableStateOf(UsersPageState())
        }

        UserList(usersPageState)
    }
}


class UsersPageState {

    init {
        println("**************USER PAGE STATE INITIALIZED***************")
    }

    private var userClickedState: String? by mutableStateOf(null)

    val userInfo: String
        get() = userClickedState ?: throw RuntimeException("users state was reset")

    val updateClickedState: (User) -> Unit = {
        if (userClicked())
            userClickedState = "$userClickedState:${it.name}"
        else
            userClickedState = it.name
    }

    fun userClicked() = userClickedState != null

    companion object {
        private val stateKey: String = "StateKey"

        val saver = mapSaver(
                save = {
                    val value = it.userClickedState ?: ""
                    mapOf(stateKey to value)
                },
                restore = { storedMap ->
                    val stored = storedMap[stateKey] as String
                    val value = if (stored == "") null else stored
                    UsersPageState().apply { userClickedState = value }
                }
        )
    }
}


@Composable
fun UserList(usersPage: UsersPageState, usersRepository: UsersRepository = DI.usersRepository) {

    @Composable
    fun displayHeaders(usersPage: UsersPageState) {
        if (usersPage.userClicked()) {
            if (usersPage.userInfo.length > 20) Text("CLICKED : ")
            Text("user clicked ${usersPage.userInfo}")
        }
    }

    @Composable
    fun displayUsersColumn(scrollState: LazyListState, users: List<User>) {
        val userComposable = displayUser(onClick = usersPage.updateClickedState)

        LazyColumn(
                state = scrollState,
                verticalArrangement = Arrangement.Center // just chek in the code
        ) {

            //srogie rozwiązanie - import androidx.compose.foundation.lazy.items
            items(users) {
                userComposable(it)
            }

        }
    }

    Column(
            modifier = Modifier.fillMaxWidth()
    ) {
        val users = usersRepository.select().toList()
        val scrollState = rememberLazyListState()
        displayColumnNavigation(scrollState, users.size)
        displayHeaders(usersPage)
        displayUsersColumn(scrollState, users)
    }
}

@Composable
fun displayColumnNavigation(scrollState: LazyListState, numberOfUsers: Int) {
    val coroutineScope = rememberCoroutineScope()

    val scrolUp: () -> Unit = {
        coroutineScope.launch {
            scrollState.animateScrollToItem(0)
        }
    }
    val scrolDown: () -> Unit = {
        coroutineScope.launch {
            scrollState.animateScrollToItem(numberOfUsers)
        }
    }


    Row(
            modifier = Modifier.padding(vertical = 10.dp),
    )
    {
        Button(onClick = scrolUp, modifier = Modifier.padding(end = 5.dp)) {
            Text("Scroll Up")
        }
        Button(onClick = scrolDown) {
            Text("Scroll Down")
        }
    }
}


fun displayUser(onClick: (User) -> Unit): @Composable (User) -> Unit = { user ->
    var isExpanded by remember { mutableStateOf(false) } // our animation state

    val paddingValue by animateDpAsState( //animation "logic"
            if (isExpanded) 48.dp else 20.dp
    )

    Surface(
            color=MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 1.dp)
    ) {
        Text(
                text = "User : ${user.name} ",
                color = Color.White,
                modifier = Modifier
                        .clickable {
                            isExpanded = !isExpanded
                            onClick(user)
                        }
                        .padding(paddingValue)  //animation styling
        )
    }

}


@Preview(showBackground = true)
@Preview(
        showBackground = true,
        widthDp = 320,
        uiMode = UI_MODE_NIGHT_YES,
        name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreview2() {
    SomeGuiApplication()
}