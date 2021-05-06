package com.aditprayogo.bajp_subs1.data.repository.tv_show

import com.aditprayogo.bajp_subs1.data.local.database.dao.TvShowDao
import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response

/**
 * Created by Aditiya Prayogo.
 */
@ExperimentalCoroutinesApi
class TvShowRepositoryTest {

    private lateinit var tvShowRepository: TvShowRepository

    private var movieServices = mock(MovieServices::class.java)
    private var tvShowDao = mock(TvShowDao::class.java)

    private val tvShows = DataDummyTemp.generateTvShowsTemp()

    private val tvShowDetail = DataDummyTemp.detailTvShow

    @Before
    fun setUp() {
        tvShowRepository = TvShowRepositoryImpl(movieServices, tvShowDao)
    }

    @Test
    fun `get discover movies and should return success`() = runBlockingTest {
        Mockito.`when`(movieServices.getDiscoverTvShows()).thenReturn(
            Response.success(
                TvShowDiscoverResponses(
                    page = 1,
                    tvShowResponses = tvShows
                )
            )
        )

        val repository = movieServices.getDiscoverTvShows()
        Mockito.verify(movieServices).getDiscoverTvShows()

        assertThat(repository).isNotNull()
        assertThat(repository.body()).isEqualTo(
            TvShowDiscoverResponses(
                page = 1,
                tvShowResponses = tvShows
            )
        )
    }

    @Test
    fun `get tv show detail and should return success`() = runBlocking {
        Mockito.`when`(movieServices.getTvShowDetail(tvShowDetail.id.toString())).thenReturn(
            Response.success(
                tvShowDetail
            )
        )

        val repository = tvShowRepository.getDetailTvShow(tvShowDetail.id.toString())
        Mockito.verify(movieServices).getTvShowDetail(tvShowDetail.id.toString())

        assertThat(repository).isNotNull()
        assertThat(repository.body()).isEqualTo(
            tvShowDetail
        )
    }

}