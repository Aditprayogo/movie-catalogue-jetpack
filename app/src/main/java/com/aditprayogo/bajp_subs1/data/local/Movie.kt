package com.aditprayogo.bajp_subs1.data.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Aditiya Prayogo.
 */
@Parcelize
data class Movie(
    val id : String?,
    val title : String?,
    val overview : String?,
    val genre : String?,
    val image : Int?,
    val dateOfRealese : String?,
    val duration : String?,
) : Parcelable