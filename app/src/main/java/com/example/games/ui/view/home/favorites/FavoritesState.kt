package com.example.games.ui.view.home.favorites

import com.example.games.data.network.model.Game

sealed class FavoritesState {
    data class ApiSuccess(val gameList: List<Game>?): FavoritesState()
    data class ApiError(val exception: Exception): FavoritesState()
    data class DeleteFavSuccess(val result: Boolean) : FavoritesState()

}