package com.aditprayogo.bajp_subs1.ui.movie


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.utils.DataDummyTemp
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

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
    lateinit var listMovieResponse: Observer<List<MovieResponses>>

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
        movieViewModel.discoverMovie.observeForever(listMovieResponse)
        movieViewModel.error.observeForever(error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `get discover movie and return success`() {
        runBlockingTest {
            val result = ResultState.Success(DataDummyTemp.discoverResponses)

            `when`(movieUseCase.getDiscoverMovie())
                .thenReturn(result)

            movieViewModel.getDiscoverMovie()

            verify(listMovieResponse, atLeastOnce()).onChanged(resultCaptor.capture())

            assertThat(result.data.movieResponses).isEqualTo(resultCaptor.allValues.first())

            clearInvocations(movieUseCase, listMovieResponse)
        }
    }
}