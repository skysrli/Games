package com.example.games.ui.view.home.games

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GamesState.ApiSuccess(emptyList()))
    val uiState: StateFlow<GamesState> = _uiState

    val isLoading = mutableStateOf(false)

    init {
        getGameList()
    }

    private fun getGameList() {
        viewModelScope.launch {
            isLoading.value = true
            apiRepository.getGameList()
                .catch {
                    isLoading.value = false

                }
                .collect { gameResult ->
                    gameResult.results?.let {
                        _uiState.value = GamesState.ApiSuccess(it)
                    }
                    isLoading.value = false
                }
        }
    }

    fun getFilteredGameList(key: String) {
        viewModelScope.launch {
            isLoading.value = true
            apiRepository.getFilteredGameList(key)
                .catch {
                    isLoading.value = false
                }
                .collect { gameResult ->
                    gameResult.results?.let {
                        _uiState.value = GamesState.ApiSuccess(it)
                    }
                    isLoading.value = false
                }
        }
    }
}