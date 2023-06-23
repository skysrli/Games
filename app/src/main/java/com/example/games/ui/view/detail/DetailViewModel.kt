package com.example.games.ui.view.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.local.repository.DatabaseRepository
import com.example.games.data.network.model.Game
import com.example.games.data.network.repository.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val db: DatabaseRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailState>(DetailState.ApiSuccess(null))
    val uiState: StateFlow<DetailState> = _uiState
    val isLoading = mutableStateOf(false)

    fun getGameDetail(id: Int) {
        viewModelScope.launch {
            isLoading.value = true
            apiRepository.getGameDetail(id)
                .catch {
                    isLoading.value = false
                }
                .collect {
                    _uiState.value = DetailState.ApiSuccess(it)
                    isLoading.value = false
                }

        }
    }

    fun addFav(game: Game) {
        viewModelScope.launch {
            isLoading.value = true
            db.insertGame(game)
                .catch {
                    isLoading.value = false
                    _uiState.value = DetailState.AddFavSuccess(false)

                }
                .collect {
                    isLoading.value = false
                    _uiState.value = DetailState.AddFavSuccess(true)
                }
        }
    }
}