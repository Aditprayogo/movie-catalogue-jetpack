package com.aditprayogo.bajp_subs1.ui.detail

import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity

/**
 * Created by Aditiya Prayogo.
 */
interface DetailViewModelContract {
    fun getMovieDetailResult(id : String)
    fun getTvShowDetailResult(id : String)
    fun getFavMovieById(id: String)
    fun insertMovieToDb(movieEntity: MovieEntity)
    fun deleteMovieFromDb(movieEntity: MovieEntity)
    fun getFavTvShowById(id: String)
    fun insertTvShowToDb(tvShowEntity: TvShowEntity)
    fun deleteTvShowFromDb(tvShowEntity: TvShowEntity)
}