package com.aditprayogo.bajp_subs1.domain.tv_show

import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepository
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response

/**
 * Created by Aditiya Prayogo.
 */
class TvShowUseCaseTest {

    private lateinit var tvShowUseCase: TvShowUseCase
    private val tvShowRepository = mock(TvShowRepository::class.java)

    private val tvShowId: String = "1"

    @Before
    fun setUp() {
        tvShowUseCase = TvShowUseCase(tvShowRepository)
    }

    @Test
    fun `get discover tv show and should return success`() {
        val original = ResultState.Success(DataDummyTemp.discoverTvShowResponses)

        val result = runBlocking {
            Mockito.`when`(tvShowRepository.getDiscoverTvShows()).thenReturn(
                Response.success(DataDummyTemp.discoverTvShowResponses)
            )
            tvShowUseCase.getDiscoverTvShows()
        }

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `get detail tv show and should return success`() {
        val original = ResultState.Success(DataDummyTemp.detailTvShow)

        val result = runBlocking {
            Mockito.`when`(tvShowRepository.getDetailTvShow(tvShowId))
                .thenReturn(Response.success(DataDummyTemp.detailTvShow))
            tvShowUseCase.getDetailTvShow(tvShowId)
        }

        assertThat(result).isEqualTo(original)

    }

}