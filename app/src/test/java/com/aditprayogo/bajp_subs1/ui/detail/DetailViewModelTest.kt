package com.aditprayogo.bajp_subs1.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*

/**
 * Created by Aditiya Prayogo.
 */
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class DetailViewModelTest {

    private lateinit var detailViewModel: DetailViewModel

    private val movieUseCase = Mockito.mock(MovieUseCase::class.java)
    private val tvShowUseCase = Mockito.mock(TvShowUseCase::class.java)

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var movieDetailResponses: Observer<MovieDetailResponse>

    @Mock
    lateinit var tvDetailResponse: Observer<TvShowDetailResponse>

    @Captor
    lateinit var resultMovieCaptor: ArgumentCaptor<MovieDetailResponse>

    @Captor
    lateinit var resultTvCaptor: ArgumentCaptor<TvShowDetailResponse>

    @Mock
    lateinit var movieListFromDb: Observer<List<MovieEntity>>

    @Captor
    lateinit var movieListFromDbArgumentCaptor: ArgumentCaptor<List<MovieEntity>>

    @Mock
    lateinit var tvShowListFromDb: Observer<List<TvShowEntity>>

    @Captor
    lateinit var tvShowListFromDbArgumentCaptor: ArgumentCaptor<List<TvShowEntity>>

    @Mock
    lateinit var insertMovieToDbStatus : Observer<Boolean>

    @Captor
    lateinit var insertMovieToDbStatusArgumentCaptor : ArgumentCaptor<Boolean>

    @Mock
    lateinit var deleteMovieToDbStatus : Observer<Boolean>

    @Captor
    lateinit var deleteMovieToDbStatusArgumentCaptor : ArgumentCaptor<Boolean>

    @Mock
    lateinit var insertTvShowToDbStatus : Observer<Boolean>

    @Captor
    lateinit var insertTvShowToDbStatusArgumentCaptor : ArgumentCaptor<Boolean>

    @Mock
    lateinit var deleteTvShowToDbStatus : Observer<Boolean>

    @Captor
    lateinit var deleteTvShowToDbStatusArgumentCaptor : ArgumentCaptor<Boolean>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        detailViewModel = DetailViewModel(movieUseCase, tvShowUseCase)
        detailViewModel.tvShowDetailResultFromApi.observeForever(tvDetailResponse)
        detailViewModel.movieDetailResultFromApi.observeForever(movieDetailResponses)
        detailViewModel.resultMovieFavFromDb.observeForever(movieListFromDb)
        detailViewModel.resultTvShowFavFromDb.observeForever(tvShowListFromDb)
        detailViewModel.resultInsertMovieToDb.observeForever(insertMovieToDbStatus)
        detailViewModel.resultDeleteMovieFromDb.observeForever(deleteMovieToDbStatus)
        detailViewModel.resultInsertTvShowToDb.observeForever(insertTvShowToDbStatus)
        detailViewModel.resultDeleteTvShowFromDb.observeForever(deleteTvShowToDbStatus)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get detail movie from api and should return success`() = runBlockingTest {

        val detailMovieData = DataDummyTemp.detailMovie
        val result = ResultState.Success(detailMovieData)

        Mockito.`when`(movieUseCase.getDetailMovie(detailMovieData.id.toString()))
            .thenReturn(result)

        detailViewModel.getMovieDetailResult(detailMovieData.id.toString())

        Mockito.verify(movieDetailResponses, atLeastOnce())
            .onChanged(resultMovieCaptor.capture())

        assertThat(result.data).isEqualTo(resultMovieCaptor.allValues.first())

        clearInvocations(movieUseCase, movieDetailResponses)
    }

    @Test
    fun `get detail tv show from api and should return success`() = runBlockingTest {

        val detailTvShowData = DataDummyTemp.detailTvShow
        val result = ResultState.Success(detailTvShowData)

        Mockito.`when`(tvShowUseCase.getDetailTvShow(detailTvShowData.id.toString()))
            .thenReturn(result)

        detailViewModel.getTvShowDetailResult(detailTvShowData.id.toString())

        Mockito.verify(tvDetailResponse, atLeastOnce()).onChanged(resultTvCaptor.capture())

        assertThat(result.data).isEqualTo(resultTvCaptor.allValues.first())

        clearInvocations(tvShowUseCase, tvDetailResponse)
    }

    @Test
    fun `get detail movie favorite from db by id and should return success`() = runBlockingTest {
        val movieData = DataDummyTemp.favoriteMovie
        val result = ResultState.Success(listOf(movieData))

        Mockito.`when`(movieData.id?.let {
            movieUseCase.getFavMovieById(it)
        }).thenReturn(result)

        movieData.id?.let {
            detailViewModel.getFavMovieById(it.toString())
        }

        verify(movieListFromDb, atLeastOnce()).onChanged(movieListFromDbArgumentCaptor.capture())
        assertThat(result.data).isEqualTo(movieListFromDbArgumentCaptor.allValues.first())
        clearInvocations(movieUseCase, movieListFromDb)
    }

    @Test
    fun `insert movie to db and should return success`() = runBlockingTest {
        val movieData = DataDummyTemp.favoriteMovie
        detailViewModel.insertMovieToDb(movieData)
        verify(movieUseCase).insertMovieToDb(movieData)
        verify(insertMovieToDbStatus, atLeastOnce()).onChanged(insertMovieToDbStatusArgumentCaptor.capture())
    }

    @Test
    fun `delete movie from db and should return success`() = runBlockingTest {
        val movieData = DataDummyTemp.favoriteMovie
        detailViewModel.deleteMovieFromDb(movieData)
        verify(movieUseCase).deleteMovieFromDb(movieData)
        verify(deleteMovieToDbStatus, atLeastOnce()).onChanged(deleteMovieToDbStatusArgumentCaptor.capture())
    }

    @Test
    fun `get detail tv show favorite from db by id and should return success`() = runBlockingTest {
        val tvShowData = DataDummyTemp.favoriteTvShow
        val result = ResultState.Success(listOf(tvShowData))

        Mockito.`when`(tvShowData.id?.let {
            tvShowUseCase.getTvShowFavById(it)
        }).thenReturn(result)

        tvShowData.id?.let {
            detailViewModel.getFavTvShowById(it.toString())
        }

        verify(tvShowListFromDb, atLeastOnce()).onChanged(tvShowListFromDbArgumentCaptor.capture())
        assertThat(result.data).isEqualTo(tvShowListFromDbArgumentCaptor.allValues.first())
        clearInvocations(tvShowUseCase, tvShowListFromDb)
    }

    @Test
    fun `insert tv show to db and should return success`() = runBlockingTest {
        val tvShowData = DataDummyTemp.favoriteTvShow
        detailViewModel.insertTvShowToDb(tvShowData)
        verify(tvShowUseCase).insertTvShowToDb(tvShowData)
        verify(insertTvShowToDbStatus, atLeastOnce()).onChanged(insertTvShowToDbStatusArgumentCaptor.capture())
    }

    @Test
    fun `delete tv show from db and should return success`() = runBlockingTest {
        val tvShowData = DataDummyTemp.favoriteTvShow
        detailViewModel.deleteTvShowFromDb(tvShowData)
        verify(tvShowUseCase).deleteTvShowFromDb(tvShowData)
        verify(deleteTvShowToDbStatus, atLeastOnce()).onChanged(deleteTvShowToDbStatusArgumentCaptor.capture())
    }

}