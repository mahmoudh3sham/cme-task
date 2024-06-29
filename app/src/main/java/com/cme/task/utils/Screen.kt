package com.cme.task.utils

sealed class Screen(val route: String) {
    data object Albums : Screen(route = "albums")
    data object AlbumDetails : Screen(route = "albumDetail/{album}")
}