package com.aditprayogo.bajp_subs1.data.remote.responses


import com.aditprayogo.bajp_subs1.BuildConfig
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
) {
    fun generateMoviePosterImage() : String {
        return BuildConfig.POSTER_SIZE_URL + posterPath
    }
}