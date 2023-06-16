package com.example.games.viewmodel.home

import com.example.games.model.Game

sealed class GameListState{
    data class Success(val gameList: List<Game>): GameListState()
    data class Error(val exception: Exception): GameListState()
}
