package com.aditprayogo.bajp_subs1.data.repository.detail

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

/**
 * Created by Aditiya Prayogo.
 */
@ExperimentalCoroutinesApi
class DetailRepositoryTest {

    private lateinit var detailRepository: DetailRepository

    private var movieServices = mock(MovieServices::class.java)

    private val movieDetail = DataDummyTemp.detailMovie
    private val tvShowDetail = DataDummyTemp.detailTvShow

    @Before
    fun setUp() {
        detailRepository = DetailRepositoryImpl(movieServices)
    }

    @Test
    fun `get movie detail and should return success`() = runBlocking {
        `when`(movieServices.getDetailMovie(movieDetail.id.toString())).thenReturn(
            Response.success(
                movieDetail
            )
        )

        val repository = detailRepository.getDetailMovie(movieDetail.id.toString())
        verify(movieServices).getDetailMovie(movieDetail.id.toString())

        assertThat(repository).isNotNull()
        assertThat(repository.body()).isEqualTo(
            movieDetail
        )
    }

    @Test
    fun `get tv show detail and should return success`() = runBlocking {
        `when`(movieServices.getTvShowDetail(tvShowDetail.id.toString())).thenReturn(
            Response.success(
                tvShowDetail
            )
        )

        val repository = detailRepository.getDetailTvShow(tvShowDetail.id.toString())
        verify(movieServices).getTvShowDetail(tvShowDetail.id.toString())

        assertThat(repository).isNotNull()
        assertThat(repository.body()).isEqualTo(
            tvShowDetail
        )
    }


}