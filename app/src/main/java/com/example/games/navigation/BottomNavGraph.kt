package com.example.games.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.games.view.home.FavoritesScreen
import com.example.games.view.home.GamesScreen

@Composable
fun BottomSheetNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Games.route) {
        composable(route = Screens.Games.route) {
            GamesScreen(navController)
        }
        composable(route = Screens.Favorites.route) {
            FavoritesScreen(navController)
        }
    }
}
