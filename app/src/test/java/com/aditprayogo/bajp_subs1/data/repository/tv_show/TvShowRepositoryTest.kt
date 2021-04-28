package com.aditprayogo.bajp_subs1.data.repository.tv_show

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    private val tvShows = DataDummyTemp.generateTvShowsTemp()

    @Before
    fun setUp() {
        tvShowRepository = TvShowRepositoryImpl(movieServices)
    }

    @Test
    fun `get discover movies and should return success`() = runBlockingTest {
        Mockito.`when`(movieServices.getDiscoverTvShows()).thenReturn(
            Response.success(
                TvShowDiscoverResponses(
                    page = 1,
                    tvShowResponses  = tvShows
                )
            )
        )

        val repository = movieServices.getDiscoverTvShows()
        Mockito.verify(movieServices).getDiscoverTvShows()

        Truth.assertThat(repository).isNotNull()
        Truth.assertThat(repository.body()).isEqualTo(
            TvShowDiscoverResponses(
                page = 1,
                tvShowResponses  = tvShows
            )
        )
    }

}