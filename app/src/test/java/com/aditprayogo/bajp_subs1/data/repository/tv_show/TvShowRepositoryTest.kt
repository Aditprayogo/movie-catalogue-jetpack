package com.aditprayogo.bajp_subs1.data.repository.tv_show

import androidx.paging.DataSource
import com.aditprayogo.bajp_subs1.data.local.database.dao.TvShowDao
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
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
import org.mockito.Mockito.*
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
        `when`(movieServices.getDiscoverTvShows()).thenReturn(
            Response.success(
                TvShowDiscoverResponses(
                    page = 1,
                    tvShowResponses = tvShows
                )
            )
        )

        val repository = movieServices.getDiscoverTvShows()
        verify(movieServices).getDiscoverTvShows()

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
        `when`(movieServices.getTvShowDetail(tvShowDetail.id.toString())).thenReturn(
            Response.success(
                tvShowDetail
            )
        )

        val repository = tvShowRepository.getDetailTvShow(tvShowDetail.id.toString())
        verify(movieServices).getTvShowDetail(tvShowDetail.id.toString())

        assertThat(repository).isNotNull()
        assertThat(repository.body()).isEqualTo(
            tvShowDetail
        )
    }

    @Test
    fun `get all tv show favorite and should return success`() = runBlocking {
        val tvShowData = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>

        `when`(tvShowDao.getAllTvShow()).thenReturn(tvShowData)

        val repository = tvShowRepository.getTvShowFavorite()
        verify(tvShowDao).getAllTvShow()

        assertThat(repository).isNotNull()
        assertThat(repository).isEqualTo(tvShowData)
    }

    @Test
    fun `get favorite tv show by id and should return success`() = runBlocking {
        val tvShowData = DataDummyTemp.favoriteTvShow

        `when`(tvShowData.id?.let { tvShowDao.getTvShowById(it) }).thenReturn(listOf(tvShowData))

        val repository = tvShowData.id?.let { tvShowRepository.getTvShowFavById(it) }
        tvShowData.id?.let { verify(tvShowDao).getTvShowById(it) }

        assertThat(repository).isNotNull()
        assertThat(repository).isEqualTo(listOf(tvShowData))
    }

    @Test
    fun `insert tv show to databaseand should return success`() = runBlocking {
        val tvShowData = DataDummyTemp.favoriteTvShow

        val repository = tvShowRepository.insertTvShowToDb(tvShowData)
        verify(tvShowDao).insertTvShowToDb(tvShowData)

        assertThat(repository).isNotNull()
    }

    @Test
    fun `delete tv show to databaseand should return success`() = runBlocking {
        val tvShowData = DataDummyTemp.favoriteTvShow

        tvShowRepository.deleteTvShowFromDb(tvShowData)
        verify(tvShowDao).deleteTvShowFromDb(tvShowData)
    }

}