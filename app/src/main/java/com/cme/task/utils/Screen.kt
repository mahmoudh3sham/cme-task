package com.cme.task.utils

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object Albums : Screen(route = "albums")

    data object AlbumDetails : Screen(
        route = "albumDetail/{albumId}",
        navArguments = listOf(navArgument("albumId") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(albumId: String) = "albumDetail/${albumId}"
    }
}