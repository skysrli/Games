package com.example.games.ui.view.home.games

import com.example.games.data.network.model.Game

sealed class GamesState{
    data class ApiSuccess(val gameList: List<Game>?): GamesState()
    data class ApiError(val exception: Exception): GamesState()

}
