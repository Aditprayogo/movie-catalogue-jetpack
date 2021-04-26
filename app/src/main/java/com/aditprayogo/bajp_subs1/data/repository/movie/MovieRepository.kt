package com.aditprayogo.bajp_subs1.data.repository.movie

import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
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