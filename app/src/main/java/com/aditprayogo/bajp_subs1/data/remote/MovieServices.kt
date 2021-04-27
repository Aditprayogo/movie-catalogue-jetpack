package com.aditprayogo.bajp_subs1.data.remote

import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Aditiya Prayogo.
 */
interface MovieServices {

    /**
     * Get discover movie
     */
    @GET("discover/movie")
    suspend fun getDiscoverMovies(): Response<MovieDiscoverResponses>

    /**
     * Get discover tv show
     */
    @GET("discover/tv")
    suspend fun getDiscoverTvShows(): Response<TvShowDiscoverResponses>

    /**
     * get detail movie
     */
    @GET("movie/{id}")
    suspend fun getDetailMovie(@Path("id") id: String): Response<MovieDetailResponse>

    /**
     * get detail tv shows
     */
    @GET("tv/{id}")
    suspend fun getTvShowDetail(@Path("id") id: String): Response<TvShowDetailResponse>


}