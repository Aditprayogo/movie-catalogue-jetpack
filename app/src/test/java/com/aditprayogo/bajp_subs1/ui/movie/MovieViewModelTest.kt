package com.aditprayogo.bajp_subs1.ui.movie


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
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
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel
    private val movieUseCase = mock(MovieUseCase::class.java)

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Mock
    lateinit var listMovieResponses: Observer<List<MovieResponses>>

    @Mock
    lateinit var error: Observer<String>

    @Captor
    lateinit var resultCaptor: ArgumentCaptor<List<MovieResponses>>

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)

        movieViewModel = MovieViewModel(movieUseCase)
        movieViewModel.resultDiscoverMovieFromApi.observeForever(listMovieResponses)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `get discover movie and return success`() {
        runBlockingTest {
            val result = ResultState.Success(DataDummyTemp.discoverMovieResponses)

            `when`(movieUseCase.getDiscoverMovie())
                .thenReturn(result)

            movieViewModel.getDiscoverMovie()

            verify(listMovieResponses, atLeastOnce()).onChanged(resultCaptor.capture())

            assertThat(result.data.movieResponses).isEqualTo(resultCaptor.allValues.first())

            clearInvocations(movieUseCase, listMovieResponses)
        }
    }
}