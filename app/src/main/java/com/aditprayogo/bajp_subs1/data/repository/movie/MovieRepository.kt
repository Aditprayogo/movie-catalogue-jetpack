package com.aditprayogo.bajp_subs1.data.repository.movie

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
    suspend fun getMoviesFavorite() : List<MovieEntity>

    suspend fun getMovieFavById(id : Int) : List<MovieEntity>

    suspend fun insertMovieToDb(movieEntity: MovieEntity)

    suspend fun deleteMovieFromDb(movieEntity: MovieEntity)
}