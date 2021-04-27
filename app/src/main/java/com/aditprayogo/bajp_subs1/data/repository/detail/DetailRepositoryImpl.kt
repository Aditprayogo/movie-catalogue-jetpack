package com.aditprayogo.bajp_subs1.data.repository.detail

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class DetailRepositoryImpl @Inject constructor(
    private val movieServices: MovieServices
) : DetailRepository {

    override suspend fun getDetailMovie(id: String): Response<MovieDetailResponse> {
        return movieServices.getDetailMovie(id)
    }

    override suspend fun getDetailTvShow(id: String): Response<TvShowDetailResponse> {
        return movieServices.getTvShowDetail(id)
    }
}