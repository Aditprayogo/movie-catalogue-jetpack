package com.aditprayogo.bajp_subs1.ui.movie

import androidx.lifecycle.ViewModel
import com.aditprayogo.bajp_subs1.utils.MovieDummy

/**
 * Created by Aditiya Prayogo.
 */
class MovieViewModel : ViewModel() {
    fun getDummyMovies() = MovieDummy.getMovies()
}