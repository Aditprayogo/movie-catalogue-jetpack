package com.aditprayogo.bajp_subs1.data.remote.responses


import com.google.gson.annotations.SerializedName

data class SpokenLanguageX(
    @SerializedName("english_name")
    val englishName: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("name")
    val name: String?
)