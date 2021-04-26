package com.aditprayogo.bajp_subs1.data.remote.responses


import com.aditprayogo.bajp_subs1.BuildConfig
import com.google.gson.annotations.SerializedName

data class MovieResponses(
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
) {
    fun getPosterMovieImage(): String {
        return BuildConfig.POSTER_SIZE_URL + backdropPath
    }
}