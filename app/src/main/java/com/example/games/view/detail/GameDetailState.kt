package com.example.games.view.detail

import com.example.games.model.GameDetail

sealed class GameDetailState {
    data class Success(val gameDetail: GameDetail?) : GameDetailState()
    data class Error(val exception: Exception) : GameDetailState()
}
