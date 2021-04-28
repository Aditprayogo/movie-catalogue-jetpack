package com.aditprayogo.bajp_subs1.domain.detail

import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.repository.detail.DetailRepository
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
class DetailUseCaseTest {

    private lateinit var detailUseCase: DetailUseCase
    private var detailRepository = mock(DetailRepository::class.java)

    private val movieId: String = "1"
    private val tvShowId: String = "1"

    @Before
    fun setUp() {
        detailUseCase = DetailUseCase(detailRepository)
    }

    @Test
    fun `get detail movie and should return success`() {
        val original = ResultState.Success(DataDummyTemp.detailMovie)

        val result = runBlocking {
            Mockito.`when`(detailRepository.getDetailMovie(movieId))
                .thenReturn(Response.success(DataDummyTemp.detailMovie))
            detailUseCase.getDetailMovie(movieId)
        }

        assertThat(result).isEqualTo(original)

    }

    @Test
    fun `get detail tv show and should return success`() {
        val original = ResultState.Success(DataDummyTemp.detailTvShow)

        val result = runBlocking {
            Mockito.`when`(detailRepository.getDetailTvShow(tvShowId))
                .thenReturn(Response.success(DataDummyTemp.detailTvShow))
            detailUseCase.getDetailTvShow(tvShowId)
        }

        assertThat(result).isEqualTo(original)

    }


}