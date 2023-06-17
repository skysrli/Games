package com.example.games.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameResult(
    @Json(name = "results")
    val results: List<Game>?,
)
