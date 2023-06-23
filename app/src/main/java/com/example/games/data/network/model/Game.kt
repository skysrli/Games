package com.example.games.data.network.model

import androidx.room.*
import com.example.games.data.network.di.NetworkModule
import com.squareup.moshi.*

@Entity(tableName = "Games")
@JsonClass(generateAdapter = true)
data class Game(
    @PrimaryKey()
    @ColumnInfo("id")
    @Json(name = "id")
    val id: Int?,

    @ColumnInfo("name")
    @Json(name = "name")
    val name: String?,

    @ColumnInfo("background_image")
    @Json(name = "background_image")
    val image: String?,

    @ColumnInfo("metacritic")
    @Json(name = "metacritic")
    val metacritic: Int?,

    @TypeConverters(GenreListConverter::class)
    @Json(name = "genres")
    val genres: List<Genre>?,

    var isShow: Boolean = false,
)


@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "name")
    val name: String?,
)

@ProvidedTypeConverter
class GenreListConverter {

    private val moshi: Moshi = NetworkModule.provideMoshi()
    private val listType = Types.newParameterizedType(List::class.java, Genre::class.java)
    private val adapter: JsonAdapter<List<Genre>> = moshi.adapter(listType)

    @TypeConverter
    fun fromGenres(genres: List<Genre>?): String? {
        return adapter.toJson(genres)
    }

    @TypeConverter
    fun toGenres(genresJson: String): List<Genre>? {
        return adapter.fromJson(genresJson)
    }
}