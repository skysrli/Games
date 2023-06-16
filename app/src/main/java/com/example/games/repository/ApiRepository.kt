package com.example.games.repository

import com.example.games.data.api.ApiServiceImpl
import com.example.games.model.GameDetail
import com.example.games.model.GameResult
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

}