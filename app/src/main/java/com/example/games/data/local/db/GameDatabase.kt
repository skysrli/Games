package com.example.games.data.local.db

import android.content.Context
import androidx.room.*
import com.example.games.DB_NAME
import com.example.games.data.network.model.Game
import com.example.games.data.network.model.Genre
import com.example.games.data.network.model.GenreListConverter
import com.squareup.moshi.Moshi


@Database(entities = [(Game::class)], version = 1, exportSchema = false)
@TypeConverters(GenreListConverter::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        fun create(context: Context): GameDatabase {
            val genreListConverter = GenreListConverter()
            return Room.databaseBuilder(context, GameDatabase::class.java, DB_NAME).addTypeConverter(genreListConverter).build()
        }
    }
}
