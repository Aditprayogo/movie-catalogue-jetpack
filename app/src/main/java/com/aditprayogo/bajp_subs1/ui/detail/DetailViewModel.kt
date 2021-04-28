package com.aditprayogo.bajp_subs1.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.domain.detail.DetailUseCase
import com.aditprayogo.bajp_subs1.utils.EspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase
): ViewModel() {

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
     * movie detail result
     */
    private val _movieDetailResultFromApi = MutableLiveData<MovieDetailResponse>()
    val movieDetailResultFromApi = _movieDetailResultFromApi

    /**
     * tv show  detail result
     */
    private val _tvShowDetailResultFromApi = MutableLiveData<TvShowDetailResponse>()
    val tvShowDetailResultFromApi = _tvShowDetailResultFromApi

    /**
     * get movie detail
     */
    fun getMovieDetailResult(id : String) {
        _state.value = LoaderState.ShowLoading
        EspressoIdlingResource.increment()

        viewModelScope.launch {
            val result = detailUseCase.getDetailMovie(id)
            _state.value = LoaderState.HideLoading
            EspressoIdlingResource.decrement()

            when(result) {
                is ResultState.Success -> _movieDetailResultFromApi.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
                is ResultState.NetworkError -> _networkError.postValue(true)
            }
        }
    }

    /**
     * get tv show detail
     */
    fun getTvShowDetailResult(id : String) {
        _state.value = LoaderState.ShowLoading
        EspressoIdlingResource.increment()

        viewModelScope.launch {
            val result = detailUseCase.getDetailTvShow(id)
            _state.value = LoaderState.HideLoading
            EspressoIdlingResource.decrement()

            when(result) {
                is ResultState.Success -> _tvShowDetailResultFromApi.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
                is ResultState.NetworkError -> _networkError.postValue(true)
            }
        }
    }

}