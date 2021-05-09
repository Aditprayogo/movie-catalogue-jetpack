package com.aditprayogo.bajp_subs1.data.repository.movie

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import retrofit2.Response

/**
 * Created by Aditiya Prayogo.
 */
interface MovieRepository {
    /**
     * Remote
     */
    suspend fun getDiscoverMovies() : Response<MovieDiscoverResponses>

    suspend fun getDetailMovie(id : String) : Response<MovieDetailResponse>

    /**
     * Local
     */
    fun getMoviesFavorite() : DataSource.Factory<Int, MovieEntity>

    suspend fun getMovieFavById(id : Int) : List<MovieEntity>

    suspend fun insertMovieToDb(movieEntity: MovieEntity)

    suspend fun deleteMovieFromDb(movieEntity: MovieEntity)
}