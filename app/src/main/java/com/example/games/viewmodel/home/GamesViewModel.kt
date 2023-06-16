package com.example.games.viewmodel.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.repository.ApiRepository
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

    // Backing property to avoid state updates from other classes
    private val _uiState = MutableStateFlow(GameListState.Success(emptyList()))

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<GameListState> = _uiState


    init {
        getGameList()
    }

    private fun getGameList() {
        viewModelScope.launch {
            apiRepository.getGameList()
                .catch {

                }
                .collect {
                    _uiState.value = GameListState.Success(it.results)
                }
        }


//            var gameList = apiRepository.getGameList()
//            Log.e("gameList", "$gameList")
//            if (gameList.isSuccessful) {
//                Log.e("response", gameList.message())
//            } else {
//
//            }
    }
}