package com.example.games.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.model.GameDetail
import com.example.games.repository.ApiRepository
import com.example.games.view.detail.GameDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(GameDetailState.Success(null))

    val uiState: StateFlow<GameDetailState> = _uiState

    init {

    }

    fun getGameDetail(id: Int){
        viewModelScope.launch {
            apiRepository.getGameDetail(id)
                .catch {

                }
                .collect{
                    _uiState.value = GameDetailState.Success(it)
                }

        }
    }
}