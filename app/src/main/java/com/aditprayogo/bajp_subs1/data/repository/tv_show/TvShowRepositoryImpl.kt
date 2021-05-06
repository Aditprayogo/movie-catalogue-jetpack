package com.aditprayogo.bajp_subs1.data.repository.tv_show

import com.aditprayogo.bajp_subs1.data.local.database.dao.TvShowDao
import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class TvShowRepositoryImpl @Inject constructor(
    private val movieServices: MovieServices,
    private val tvShowDao: TvShowDao
) : TvShowRepository {

    override suspend fun getDiscoverTvShows(): Response<TvShowDiscoverResponses> {
        return movieServices.getDiscoverTvShows()
    }

    override suspend fun getDetailTvShow(id: String): Response<TvShowDetailResponse> {
        return movieServices.getTvShowDetail(id)
    }
}