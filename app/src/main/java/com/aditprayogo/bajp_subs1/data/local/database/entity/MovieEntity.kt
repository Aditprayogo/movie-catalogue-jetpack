package com.aditprayogo.bajp_subs1.data.local.database.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Aditiya Prayogo.
 */
@Parcelize
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") val id: Int?,

    @ColumnInfo(name = "posterPath") val posterPath: String?,

    @ColumnInfo(name = "overview") val overview: String?,

    @ColumnInfo(name = "releaseDate") val releaseDate: String?,

    @ColumnInfo(name = "status") val status: String?,

    @ColumnInfo(name = "title") val title: String?,

    @ColumnInfo(name = "voteAverage") val voteAverage: Double?,

    @ColumnInfo(name = "genres") val genres: String?,

) : Parcelable