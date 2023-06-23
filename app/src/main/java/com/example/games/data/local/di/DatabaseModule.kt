package com.example.games.data.local.di

import android.content.Context
import com.example.games.data.local.db.GameDao
import com.example.games.data.local.db.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase =
        GameDatabase.create(context = context)

    @Singleton
    @Provides
    fun provideDao(database: GameDatabase): GameDao {
        return database.gameDao()
    }
}