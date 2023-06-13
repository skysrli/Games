package com.example.games.navigation

sealed class Screens(val route: String){
    object Home: Screens("home")
    object Detail: Screens("detail/{game_id}")
    object Games: Screens("home/games")
    object Favorites: Screens("home/favorites")
}
