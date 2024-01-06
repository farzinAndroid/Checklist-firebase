package com.farzin.checklist.navGraph

sealed class Screens(val route:String){

    object CreateUserScreen : Screens(route = "create_user_screen")
    object HomeScreen : Screens(route = "home_screen")

}
