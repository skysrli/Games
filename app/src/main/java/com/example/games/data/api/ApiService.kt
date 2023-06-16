package com.example.games.data.api

import com.example.games.API_KEY
import com.example.games.model.GameDetail
import com.example.games.model.GameResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGameList(
        @Query("page_size") pageSize: Int = 10,
        @Query("page") page: Int = 1,
        @Query("key") apiKey: String = API_KEY,
    ): GameResult

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int = 0,
        @Query("key") apiKey: String = API_KEY,
    ): GameDetail
}