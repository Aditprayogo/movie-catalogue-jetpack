package com.aditprayogo.bajp_subs1.data.remote

import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Aditiya Prayogo.
 */
interface MovieServices {

    /**
     * Get discover movie
     */
    @GET("discover/movie")
    suspend fun getDiscoverMovies() : Response<MovieDiscoverResponses>

    /**
     * Get discover tv show
     */
    @GET("discover/tv")
    suspend fun getDiscoverTvShows() : Response<TvShowDiscoverResponses>


}