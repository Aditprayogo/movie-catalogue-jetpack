package com.aditprayogo.bajp_subs1.data.repository.tv_show

import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
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
    suspend fun getTvShowFavorite() : List<TvShowEntity>

    suspend fun getTvShowFavById(id : Int) : List<TvShowEntity>

    suspend fun insertTvShowToDb(tvShowEntity: TvShowEntity)

    suspend fun deleteTvShowFromDb(tvShowEntity: TvShowEntity)

}