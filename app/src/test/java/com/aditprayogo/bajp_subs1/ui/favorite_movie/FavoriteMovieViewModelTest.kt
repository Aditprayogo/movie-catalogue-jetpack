package com.aditprayogo.bajp_subs1.ui.favorite_movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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
    private lateinit var movieList : Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.initMocks(this)

        favoriteMovieViewModel = FavoriteMovieViewModel(movieUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `get movie favorite from db and should return success1`() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(5)

        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieUseCase.getMoviesFavorite()).thenReturn(movies)
        val movie = favoriteMovieViewModel.getFavoriteMovies().value

        verify(movieUseCase).getMoviesFavorite()

        assertThat(movie).isNotNull()
        assertThat(movie?.size).isEqualTo(5)

        favoriteMovieViewModel.getFavoriteMovies().observeForever(movieList)
        verify(movieList).onChanged(dummyMovie)
    }

}