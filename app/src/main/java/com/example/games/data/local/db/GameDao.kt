package com.example.games.data.local.db

import androidx.room.*
import com.example.games.data.network.model.Game

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game)

    @Query("Select * from games where id = :id")
    suspend fun getGame(id: Int): Game

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("Select * from games")
    suspend fun getAllGame(): List<Game>
}