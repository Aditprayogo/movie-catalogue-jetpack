package com.aditprayogo.bajp_subs1.data.repository.movie

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
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
class MovieRepositoryTest {

    private lateinit var movieRepository: MovieRepository

    private var movieServices = mock(MovieServices::class.java)

    private val movies = DataDummyTemp.generateMovieTemp()

    @Before
    fun setUp() {
        movieRepository = MovieRepositoryImpl(movieServices)
    }

    @Test
    fun `get discover movies and should return success`() = runBlocking {
        `when`(movieServices.getDiscoverMovies()).thenReturn(
            Response.success(
                MovieDiscoverResponses(
                    page = 1,
                    movieResponses = movies
                )
            )
        )

        val repository = movieRepository.getDiscoverMovies()
        verify(movieServices).getDiscoverMovies()

        assertThat(repository).isNotNull()
        assertThat(repository.body()).isEqualTo(
            MovieDiscoverResponses(
                page = 1,
                movieResponses = movies
            )
        )
    }

}