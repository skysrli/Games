package com.example.games.data.local.repository

import com.example.games.data.local.db.GameDaoImpl
import com.example.games.data.network.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseRepository @Inject constructor(private val gameDaoImpl: GameDaoImpl) {

    fun insertGame(game: Game) = flow {
        emit(gameDaoImpl.insertGame(game = game))
    }.flowOn(Dispatchers.IO)

    fun getGame(id: Int): Flow<Game> = flow<Game> {
        emit(gameDaoImpl.getGame(id))
    }.flowOn(Dispatchers.IO)

    fun deleteGame(game: Game) = flow {
        emit(gameDaoImpl.deleteGame(game = game))
    }.flowOn(Dispatchers.IO)

    fun getAllGame(): Flow<List<Game>> = flow {
        emit(gameDaoImpl.getAllGame())
    }.flowOn(Dispatchers.IO)

}