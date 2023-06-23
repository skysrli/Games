package com.example.games.data.network.repository

import com.example.games.data.network.api.ApiServiceImpl
import com.example.games.data.network.model.GameDetail
import com.example.games.data.network.model.GameResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val apiServiceImpl: ApiServiceImpl,
) {

    fun getGameList(): Flow<GameResult> = flow {
        emit(apiServiceImpl.getGameList())
    }.flowOn(Dispatchers.IO)

    fun getGameDetail(id: Int): Flow<GameDetail> = flow {
        emit(apiServiceImpl.getGameDetail(id))
    }.flowOn(Dispatchers.IO)

    fun getFilteredGameList(key: String): Flow<GameResult> = flow {
        emit(apiServiceImpl.getFilteredGameList(key))
    }.flowOn(Dispatchers.IO)
}