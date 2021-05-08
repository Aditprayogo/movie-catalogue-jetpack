package com.aditprayogo.bajp_subs1.domain.tv_show

import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepository
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
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
            `when`(
                tvShowRepository.getDiscoverTvShows()
            ).thenReturn(
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
            `when`(
                tvShowRepository.getDetailTvShow(tvShowId)
            ).thenReturn(
                Response.success(DataDummyTemp.detailTvShow)
            )

            tvShowUseCase.getDetailTvShow(tvShowId)
        }

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `get all favorite tv show from db and should return success`() {
        val tvShowData = DataDummyTemp.listFavoriteTvShow

        val original = ResultState.Success(tvShowData)

        val result = runBlocking {
            `when`(
                tvShowRepository.getTvShowFavorite()
            ).thenReturn(
                tvShowData
            )

            tvShowUseCase.getTvShowFavorite()
        }

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `get favorite tv by id show from db and should return success`() {
        val tvShowData = DataDummyTemp.favoriteTvShow

        val original = ResultState.Success(listOf(tvShowData))

        val result = runBlocking {
            `when`(
                tvShowData.id?.let { tvShowRepository.getTvShowFavById(it) }
            ).thenReturn(
                listOf(tvShowData)
            )

            tvShowData.id?.let { tvShowUseCase.getTvShowFavById(it) }
        }

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `insert tv show to db and should return success`() {
        val tvShowData =  DataDummyTemp.favoriteTvShow

        runBlocking {
            tvShowUseCase.insertTvShowToDb(tvShowData)
            verify(tvShowRepository).insertTvShowToDb(tvShowData)
        }

    }

    @Test
    fun `delete tv show to db and should return success`() {
        val tvShowData =  DataDummyTemp.favoriteTvShow

        runBlocking {
            tvShowUseCase.deleteTvShowFromDb(tvShowData)
            verify(tvShowRepository).deleteTvShowFromDb(tvShowData)
        }

    }




}