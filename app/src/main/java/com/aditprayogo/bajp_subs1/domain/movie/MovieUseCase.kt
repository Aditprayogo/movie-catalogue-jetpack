package com.aditprayogo.bajp_subs1.domain.movie

import com.aditprayogo.bajp_subs1.core.state.ResultState
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

}