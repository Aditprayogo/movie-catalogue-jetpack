package com.aditprayogo.bajp_subs1.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.domain.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    /**
     * Loader State
     */
    private val _state = MutableLiveData<LoaderState>()
    val state = _state

    /**
     * Error request
     */
    private val _error = MutableLiveData<String?>()
    val error = _error

    /**
     * Network Error
     */
    private val _networkError = MutableLiveData<Boolean>()
    val networkError = _networkError

    /**
     * Discover movie Result
     */
    private val _discoverMovie = MutableLiveData<List<MovieResponses>?>()
    val discoverMovie = _discoverMovie

    init {
        getDiscoverMovie()
    }

    /**
     * get discover movie
     */
    fun getDiscoverMovie() {
        _state.value = LoaderState.ShowLoading

        viewModelScope.launch {
            val result = movieUseCase.getDiscoverMovie()
            _state.value = LoaderState.HideLoading

            when (result) {
                is ResultState.Success -> _discoverMovie.postValue(result.data?.movieResponses)
                is ResultState.Error -> _error.postValue(result.error)
                is ResultState.NetworkError -> _networkError.postValue(true)
            }
        }
    }
}