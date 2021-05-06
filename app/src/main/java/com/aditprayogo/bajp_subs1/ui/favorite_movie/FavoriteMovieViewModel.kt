package com.aditprayogo.bajp_subs1.ui.favorite_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.utils.EspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    /**
     * Error request
     */
    private val _error = MutableLiveData<String?>()
    val error = _error

    /**
     * Discover movie Result
     */
    private val _resultMovieFromDb = MutableLiveData<List<MovieEntity>>()
    val resultMovieFromDb = _resultMovieFromDb

    init {
        getMovieFavorite()
    }

    /**
     * get discover movie
     */
    fun getMovieFavorite() {
        EspressoIdlingResource.increment()
        viewModelScope.launch {
            EspressoIdlingResource.decrement()
            when ( val result = movieUseCase.getMoviesFavorite()) {
                is ResultState.Success -> _resultMovieFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

}