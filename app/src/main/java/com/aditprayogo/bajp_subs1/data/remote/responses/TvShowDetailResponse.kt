package com.aditprayogo.bajp_subs1.data.remote.responses


import com.aditprayogo.bajp_subs1.BuildConfig
import com.google.gson.annotations.SerializedName

data class TvShowDetailResponse(
    @SerializedName("genres")
    val genres: List<GenreX>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("first_air_date")
    val firstAirDate: String,
) {
    fun generateImageTvDetail(): String {
        return BuildConfig.POSTER_SIZE_URL + posterPath
    }
}