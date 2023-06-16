package com.example.games.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameDetail(
    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "background_image")
    val image: String,

    @Json(name = "reddit_url")
    val redditUrl: String,

    @Json(name = "website")
    val website: String,
)
