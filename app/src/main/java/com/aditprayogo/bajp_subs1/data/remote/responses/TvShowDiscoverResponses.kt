package com.aditprayogo.bajp_subs1.data.remote.responses


import com.google.gson.annotations.SerializedName

data class TvShowDiscoverResponses(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val tvShowResponses: List<TvShowResponses>?,
)