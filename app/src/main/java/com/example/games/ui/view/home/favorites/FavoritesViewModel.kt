package com.example.games.ui.view.home.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.local.repository.DatabaseRepository
import com.example.games.data.network.model.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val databaseRepository: DatabaseRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<FavoritesState>(FavoritesState.ApiSuccess(emptyList()))
    val uiState: StateFlow<FavoritesState> = _uiState

    val isLoading = mutableStateOf(false)

    init {
        getGameList()
    }

    fun getGameList() {
        viewModelScope.launch {
            isLoading.value = true
            databaseRepository.getAllGame()
                .catch {
                    isLoading.value = false

                }
                .collect {
                    _uiState.value = FavoritesState.ApiSuccess(it)
                    isLoading.value = false
                }
        }
    }


    fun deleteGameFromFav(game: Game) {
        viewModelScope.launch {
            isLoading.value = true
            databaseRepository.deleteGame(game = game)
                .catch {
                    isLoading.value = false
                    _uiState.value = FavoritesState.DeleteFavSuccess(false)
                }
                .collect {
                    isLoading.value = false
                    _uiState.value = FavoritesState.DeleteFavSuccess(true)
                    getGameList()
                }
        }
    }
}