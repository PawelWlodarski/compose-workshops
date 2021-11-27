package com.wlodar.jug.compose.gui.data

data class User(val login:String, val name:String)


interface UsersRepository{
    fun select():Collection<User>
}

object InMemoryUsersRepository:UsersRepository{
    private var users = listOf(
        User("user1@gmail.com","user1"),
        User("user2@gmail.com","user2"),
        User("user3@gmail.com","user3")
    )

    override fun select():Collection<User> = users
}

object InMemorySourceOfALotOfUsers:UsersRepository{
    private var users = (1 .. 100).map {
        User("user$it@gmail.com","user$it")
    }


    override fun select():Collection<User> = users
}

