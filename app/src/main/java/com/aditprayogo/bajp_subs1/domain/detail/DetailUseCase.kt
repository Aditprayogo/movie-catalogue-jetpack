package com.aditprayogo.bajp_subs1.domain.detail

import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.data.repository.detail.DetailRepository
import com.aditprayogo.bajp_subs1.utils.safeApiCall
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
class DetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    suspend fun getDetailMovie(id : String) : ResultState<MovieDetailResponse> {
        return safeApiCall {
            val response = detailRepository.getDetailMovie(id)
            try {
                ResultState.Success(response.body()!!)
            } catch (e : Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

    suspend fun getDetailTvShow(id : String) : ResultState<TvShowDetailResponse> {
        return safeApiCall {
            val response = detailRepository.getDetailTvShow(id)
            try {
                ResultState.Success(response.body()!!)
            } catch (e : Exception) {
                ResultState.Error(e.localizedMessage, response.code())
            }
        }
    }

}