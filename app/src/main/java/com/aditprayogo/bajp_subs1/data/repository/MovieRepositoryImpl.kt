package com.aditprayogo.bajp_subs1.data.repository

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieServices: MovieServices
) : MovieRepository {

    override suspend fun getDiscoverMovies(): Response<MovieDiscoverResponses> {
        return movieServices.getDiscoverMovies()
    }
}