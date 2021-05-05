package com.aditprayogo.bajp_subs1.data.repository.tv_show

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

    /**
     * Local
     */

}