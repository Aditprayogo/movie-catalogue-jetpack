package com.aditprayogo.bajp_subs1.data.repository

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

}