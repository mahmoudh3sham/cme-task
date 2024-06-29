package com.cme.task.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cme.task.albums.AlbumsScreen
import com.cme.task.details.AlbumDetailsScreen

@Composable
fun AlbumsAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Albums.route) {
        composable(route = Screen.Albums.route) {
            AlbumsScreen(
                onAlbumClick = {
                    navController.navigate(
                        Screen.AlbumDetails.createRoute(
                            albumId = it.id.toString()
                        )
                    )
                }
            )
        }
        composable(
            route = Screen.AlbumDetails.route,
            arguments = Screen.AlbumDetails.navArguments
        ){
            AlbumDetailsScreen(
                onBackClick = { navController.navigateUp() },
            )
        }
    }
}