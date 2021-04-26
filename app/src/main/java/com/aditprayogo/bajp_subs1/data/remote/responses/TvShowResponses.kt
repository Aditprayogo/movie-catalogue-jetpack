package com.aditprayogo.bajp_subs1.data.remote.responses


import com.aditprayogo.bajp_subs1.BuildConfig
import com.google.gson.annotations.SerializedName

data class TvShowResponses(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
) {
    fun getImagePoster() : String {
        return BuildConfig.POSTER_SIZE_URL + backdropPath
    }
}