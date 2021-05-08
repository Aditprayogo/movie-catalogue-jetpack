package com.aditprayogo.bajp_subs1.domain.movie

import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.repository.movie.MovieRepository
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
class MovieUseCaseTest {

    private var movieRepository = mock(MovieRepository::class.java)
    private lateinit var movieUseCase: MovieUseCase

    private val movieId: String = "1"

    @Before
    fun setUp() {
        movieUseCase = MovieUseCase(movieRepository)
    }

    @Test
    fun `get discover movie and should return success`() {
        val original = ResultState.Success(DataDummyTemp.discoverMovieResponses)

        val result = runBlocking {
            `when`(movieRepository.getDiscoverMovies())
                .thenReturn(Response.success(DataDummyTemp.discoverMovieResponses))

            movieUseCase.getDiscoverMovie()
        }

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `get detail movie and should return success`() {
        val original = ResultState.Success(DataDummyTemp.detailMovie)

        val result = runBlocking {
            `when`(movieRepository.getDetailMovie(movieId))
                .thenReturn(Response.success(DataDummyTemp.detailMovie))
            movieUseCase.getDetailMovie(movieId)
        }

        assertThat(result).isEqualTo(original)

    }

    @Test
    fun `get favorite movie and should return success`() {
        val movieData = DataDummyTemp.listFavoriteMovie

        val original = ResultState.Success(movieData)

        val result = runBlocking {
            `when`(movieRepository.getMoviesFavorite()).thenReturn(movieData)
            movieUseCase.getMoviesFavorite()
        }

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `get favorite movie by id and should return success`() {
        val movieData = DataDummyTemp.favoriteMovie

        val original = ResultState.Success(listOf(movieData))

        val result = runBlocking {
            `when`(movieData.id?.let { movieRepository.getMovieFavById(it) }).thenReturn(listOf(movieData))
            movieData.id?.let { movieUseCase.getFavMovieById(it) }
        }

        assertThat(result).isEqualTo(original)
    }

    @Test
    fun `insert movie to db and should return success`() {
        val movieData =  DataDummyTemp.favoriteMovie

        runBlocking {
            movieUseCase.insertMovieToDb(movieData)
            verify(movieRepository).insertMovieToDb(movieData)
        }

    }

    @Test
    fun `delete movie from db and should return success`() {
        val movieData =  DataDummyTemp.favoriteMovie

        runBlocking {
            movieUseCase.deleteMovieFromDb(movieData)
            verify(movieRepository).deleteMovieFromDb(movieData)
        }

    }

}