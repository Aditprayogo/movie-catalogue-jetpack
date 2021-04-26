package com.aditprayogo.bajp_subs1.data.remote.responses


import com.google.gson.annotations.SerializedName

data class MovieDiscoverResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieResponses: List<MovieResponses>
)