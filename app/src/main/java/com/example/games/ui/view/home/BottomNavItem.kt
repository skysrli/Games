package com.example.games.ui.view.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.games.R
import com.example.games.ui.navigation.Screens

sealed class BottomNavItem(
    val route:String,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int
){
    object Games: BottomNavItem(
        route = Screens.Games.route,
        titleResId = R.string.bottom_nav_item_games,
        iconResId = R.drawable.ic_game_icon
    )

    object Favorites: BottomNavItem(
        route = Screens.Favorites.route,
        titleResId = R.string.bottom_nav_item_favorites,
        iconResId = R.drawable.ic_star_icon
    )
}
