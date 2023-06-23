package com.example.games.ui.view.detail

import com.example.games.data.network.model.GameDetail

sealed class DetailState {
    data class ApiSuccess(val gameDetail: GameDetail?) : DetailState()
    data class ApiError(val exception: Exception) : DetailState()
    data class AddFavSuccess(val result: Boolean) : DetailState()
}
