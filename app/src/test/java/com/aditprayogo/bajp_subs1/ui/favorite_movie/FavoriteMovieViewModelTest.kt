package com.aditprayogo.bajp_subs1.ui.favorite_movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
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
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by Aditiya Prayogo.
 */
@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class FavoriteMovieViewModelTest {

    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel
    private val movieUseCase = mock(MovieUseCase::class.java)

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var movieList : Observer<List<MovieEntity>>

    @Captor
    lateinit var movieListArgumentCaptor : ArgumentCaptor<List<MovieEntity>>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        favoriteMovieViewModel = FavoriteMovieViewModel(movieUseCase)
        favoriteMovieViewModel.resultMovieFromDb.observeForever(movieList)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get movie favorite from db and should return success`() = runBlockingTest {
        val moviesData = DataDummyTemp.listFavoriteMovie
        val result = ResultState.Success(moviesData)

        `when`(movieUseCase.getMoviesFavorite()).thenReturn(result)

        favoriteMovieViewModel.getMovieFavorite()

        verify(movieList, atLeastOnce()).onChanged(movieListArgumentCaptor.capture())
        assertThat(result.data).isEqualTo(movieListArgumentCaptor.allValues.first())
        clearInvocations(movieUseCase, movieList)
    }

}