package com.aditprayogo.bajp_subs1.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.clearInvocations
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

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        detailViewModel = DetailViewModel(movieUseCase, tvShowUseCase)
        detailViewModel.tvShowDetailResultFromApi.observeForever(tvDetailResponse)
        detailViewModel.movieDetailResultFromApi.observeForever(movieDetailResponses)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get detail movie and should return success`() = runBlockingTest {

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
    fun `get tv show movie and should return success`() = runBlockingTest {

        val detailTvShowData = DataDummyTemp.detailTvShow
        val result = ResultState.Success(detailTvShowData)

        Mockito.`when`(tvShowUseCase.getDetailTvShow(detailTvShowData.id.toString()))
            .thenReturn(result)

        detailViewModel.getTvShowDetailResult(detailTvShowData.id.toString())

        Mockito.verify(tvDetailResponse, atLeastOnce()).onChanged(resultTvCaptor.capture())

        assertThat(result.data).isEqualTo(resultTvCaptor.allValues.first())

        clearInvocations(tvShowUseCase, tvDetailResponse)

    }

}