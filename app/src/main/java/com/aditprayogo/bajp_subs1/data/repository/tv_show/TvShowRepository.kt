package com.aditprayogo.bajp_subs1.data.repository.tv_show

import androidx.paging.DataSource
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import retrofit2.Response

/**
 * Created by Aditiya Prayogo.
 */
interface TvShowRepository {
    /**
     * Remote
     */
    suspend fun getDiscoverTvShows() : Response<TvShowDiscoverResponses>

    suspend fun getDetailTvShow(id : String) : Response<TvShowDetailResponse>

    /**
     * Local
     */
    fun getTvShowFavorite() : DataSource.Factory<Int, TvShowEntity>

    suspend fun getTvShowFavById(id : Int) : List<TvShowEntity>

    suspend fun insertTvShowToDb(tvShowEntity: TvShowEntity)

    suspend fun deleteTvShowFromDb(tvShowEntity: TvShowEntity)

}