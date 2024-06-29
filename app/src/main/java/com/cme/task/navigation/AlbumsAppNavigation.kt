package com.cme.task.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cme.domain.model.Album
import com.cme.task.albums.AlbumsScreen
import com.cme.task.details.AlbumDetailsScreen
import com.cme.task.utils.Screen

@Composable
fun AlbumsAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Albums.route, exitTransition = {ExitTransition.None}) {
        composable(route = Screen.Albums.route) {
            AlbumsScreen(
                onAlbumClick = { album ->
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "album",
                        value = album
                    )
                    navController.navigate(
                        Screen.AlbumDetails.route
                    )
                }
            )
        }
        composable(
            route = Screen.AlbumDetails.route
        ){
            val album = navController.previousBackStackEntry?.savedStateHandle?.get<Album>("album")
            AlbumDetailsScreen(
                album
            )
        }
    }
}