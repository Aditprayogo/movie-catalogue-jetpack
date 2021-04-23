package com.aditprayogo.bajp_subs1.data.remote.responses


import com.google.gson.annotations.SerializedName

data class GenreX(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)