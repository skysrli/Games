package com.example.games.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.games.view.detail.DetailScreen
import com.example.games.view.home.FavoritesScreen
import com.example.games.view.home.GamesScreen

@Composable
fun AppNavGraph(navController: NavHostController,modifier: Modifier) {
    NavHost(navController = navController, startDestination = Screens.Home.route, modifier = modifier) {
        navigation(startDestination = Screens.Games.route, route = Screens.Home.route) {
            composable(route = Screens.Games.route) {
                GamesScreen(navController)
            }
            composable(route = Screens.Favorites.route) {
                FavoritesScreen(navController)
            }
        }
        composable(route = Screens.Detail.route) {
            DetailScreen(navController)
        }
    }
}
