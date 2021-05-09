package com.aditprayogo.bajp_subs1.ui.favorite_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _error = MutableLiveData<String?>()
    val error = _error

    fun getFavoriteMovies() = movieUseCase.getMoviesFavorite()

}
