package com.gladiator.BasketBuddy.model

sealed class Screen (val route: String){

    object Splash :Screen("splash")
    object SignUp :Screen("signup")
    object Login :Screen("login")
    object Home :Screen("home")
    object Collaboration :Screen("collaboration")
    object ListDisplay :Screen("listDisplay")
    object ItemDisplay: Screen("itemDisplay")
    object AddList: Screen("addList")
    object AddItem: Screen("addItem")


}