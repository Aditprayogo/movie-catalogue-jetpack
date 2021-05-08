package com.aditprayogo.bajp_subs1.domain.movie

import androidx.paging.DataSource
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import com.aditprayogo.bajp_subs1.data.repository.movie.MovieRepository
import com.aditprayogo.bajp_subs1.utils.safeApiCall
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class MovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    /**
     * Discover movie from API
     */
    suspend fun getDiscoverMovie(): ResultState<MovieDiscoverResponses> {
        return safeApiCall {
            val response = movieRepository.getDiscoverMovies()
            try {
                ResultState.Success(response.body()!!)
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getDetailMovie(id: String): ResultState<MovieDetailResponse> {
        return safeApiCall {
            val response = movieRepository.getDetailMovie(id)
            try {
                ResultState.Success(response.body()!!)
            } catch (e: java.lang.Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    /**
     * Local
     */
    suspend fun getMoviesFavorite(): ResultState<DataSource.Factory<Int, MovieEntity>> {
        return try {
            val result = movieRepository.getMoviesFavorite()
            ResultState.Success(result)
        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage, 500)
        }
    }

    suspend fun getFavMovieById(id: Int): ResultState<List<MovieEntity>> {
        return try {
            val result = movieRepository.getMovieFavById(id)
            ResultState.Success(result)
        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage, 500)
        }
    }

    suspend fun insertMovieToDb(movieEntity: MovieEntity) {
        return try {
            movieRepository.insertMovieToDb(movieEntity)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun deleteMovieFromDb(movieEntity: MovieEntity) {
        return try {
            movieRepository.deleteMovieFromDb(movieEntity)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

}