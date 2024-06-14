package com.nqmgaming.test.presentation.utils

sealed class Screen (val route: String) {
    data object Splash: Screen("splash")
    data object Home: Screen("home")
    data object AddItem: Screen("add_item")
    data object Detail: Screen("detail")
}