package com.aditprayogo.bajp_subs1.domain.tv_show

import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepository
import com.aditprayogo.bajp_subs1.utils.safeApiCall
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class TvShowUseCase @Inject constructor(private val tvShowRepository: TvShowRepository) {
    /**
     * Discover tv shows from api
     */
    suspend fun getDiscoverTvShows(): ResultState<TvShowDiscoverResponses> {
        return safeApiCall {
            val response = tvShowRepository.getDiscoverTvShows()
            try {
                ResultState.Success(response.body()!!)
            } catch (e: Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getDetailTvShow(id : String) : ResultState<TvShowDetailResponse> {
        return safeApiCall {
            val response = tvShowRepository.getDetailTvShow(id)
            try {
                ResultState.Success(response.body()!!)
            } catch (e : java.lang.Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }
}