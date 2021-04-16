package com.aditprayogo.bajp_subs1.ui.movie


import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by Aditiya Prayogo.
 */
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        viewModel = MovieViewModel()
    }

    @Test
    fun `get movies from dummy movies returns sucess`() {
        val movie = viewModel.getDummyMovies()

        assertThat(movie).isNotNull()
        assertThat(movie.size).isEqualTo(10)
    }

}