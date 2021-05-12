package com.aditprayogo.bajp_subs1.data.repository.movie

import androidx.paging.DataSource
import com.aditprayogo.bajp_subs1.data.local.database.dao.MovieDao
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
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
    private var movieDao = mock(MovieDao::class.java)

    private val movies = DataDummyTemp.generateMovieTemp()

    private val movieDetail = DataDummyTemp.detailMovie

    @Before
    fun setUp() {
        movieRepository = MovieRepositoryImpl(movieServices, movieDao)
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

    @Test
    fun `get movie detail and should return success`() = runBlocking {
        `when`(movieServices.getDetailMovie(movieDetail.id.toString())).thenReturn(
            Response.success(
                movieDetail
            )
        )

        val repository = movieRepository.getDetailMovie(movieDetail.id.toString())
        verify(movieServices).getDetailMovie(movieDetail.id.toString())

        assertThat(repository).isNotNull()
        assertThat(repository.body()).isEqualTo(
            movieDetail
        )
    }

    @Test
    fun `get favorite movie from db and should return success`() = runBlocking {
        val listFavMovies = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>

        `when`(movieDao.getAllMovies()).thenReturn(listFavMovies)

        val repository = movieRepository.getMoviesFavorite()
        verify(movieDao).getAllMovies()

        assertThat(repository).isNotNull()
        assertThat(repository).isEqualTo(listFavMovies)
    }

    @Test
    fun `get favorite movie by id and should return success`() = runBlocking {
        val movie = DataDummyTemp.favoriteMovie

        `when`(movie.id?.let { movieDao.getMovieFavById(it) }).thenReturn(listOf(movie))

        val repository = movie.id?.let { movieRepository.getMovieFavById(it) }
        movie.id?.let { verify(movieDao).getMovieFavById(it) }

        assertThat(repository).isNotNull()
        assertThat(repository).isEqualTo(listOf(movie))
    }

    @Test
    fun `insert movie to db and should return success`() = runBlocking {
        val movie = DataDummyTemp.favoriteMovie

        val repository = movieRepository.insertMovieToDb(movie)
        verify(movieDao).insertMovieToDb(movie)

        assertThat(repository).isNotNull()
    }

    @Test
    fun `delete movie to db and should return success`() = runBlocking {
        val movie = DataDummyTemp.favoriteMovie

        movieRepository.deleteMovieFromDb(movie)
        verify(movieDao).deleteMovieFromDb(movie)
    }

}