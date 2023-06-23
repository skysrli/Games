package com.example.games.data.local.db

import com.example.games.data.network.model.Game
import javax.inject.Inject

class GameDaoImpl @Inject constructor(private val dao: GameDao) {

    suspend fun insertGame(game: Game) = dao.insertGame(game)

    suspend fun getGame(id: Int): Game = dao.getGame(id)

    suspend fun deleteGame(game: Game) = dao.deleteGame(game = game)

    suspend fun getAllGame(): List<Game> = dao.getAllGame()
}