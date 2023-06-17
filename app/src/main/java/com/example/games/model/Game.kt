package com.example.games.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Game(
    @Json(name = "id")
    val id: Int?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "background_image")
    val image: String?,

    @Json(name = "metacritic")
    val metacritic: Int?,

    @Json(name = "genres")
    val genres: List<Genre>?,

    val isFavorite:Boolean = false
)

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "name")
    val name: String?,
)