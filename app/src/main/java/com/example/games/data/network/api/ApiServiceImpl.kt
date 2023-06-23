package com.example.games.data.network.api

import com.example.games.data.network.model.GameDetail
import com.example.games.data.network.model.GameResult
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getGameList(): GameResult = apiService.getGameList()

    suspend fun getGameDetail(id: Int): GameDetail = apiService.getGameDetail(id)

    suspend fun getFilteredGameList(key: String): GameResult = apiService.getFilteredGameList(searchKey = key)
}