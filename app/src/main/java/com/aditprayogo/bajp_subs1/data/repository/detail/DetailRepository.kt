package com.aditprayogo.bajp_subs1.data.repository.detail

import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import retrofit2.Response

/**
 * Created by Aditiya Prayogo.
 */
interface  DetailRepository {
    suspend fun getDetailMovie(id : String) : Response<MovieDetailResponse>
    suspend fun getDetailTvShow(id : String) : Response<TvShowDetailResponse>
}
