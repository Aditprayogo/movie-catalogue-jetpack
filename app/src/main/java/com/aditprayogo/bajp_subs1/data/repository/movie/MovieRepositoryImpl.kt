package com.aditprayogo.bajp_subs1.data.repository.movie

import androidx.paging.DataSource
import com.aditprayogo.bajp_subs1.data.local.database.dao.MovieDao
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieServices: MovieServices,
    private val movieDao: MovieDao
) : MovieRepository {

    /**
     * Remote
     */
    override suspend fun getDiscoverMovies(): Response<MovieDiscoverResponses> {
        return movieServices.getDiscoverMovies()
    }

    override suspend fun getDetailMovie(id: String): Response<MovieDetailResponse> {
        return movieServices.getDetailMovie(id)
    }

    /**
     * Local
     */
    override fun getMoviesFavorite(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getAllMovies()
    }

    override suspend fun getMovieFavById(id: Int): List<MovieEntity> {
        return movieDao.getMovieFavById(id)
    }

    override suspend fun insertMovieToDb(movieEntity: MovieEntity) {
        return movieDao.insertMovieToDb(movieEntity)
    }

    override suspend fun deleteMovieFromDb(movieEntity: MovieEntity) {
        return movieDao.deleteMovieFromDb(movieEntity)
    }

}