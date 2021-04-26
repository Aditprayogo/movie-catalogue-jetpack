package com.aditprayogo.bajp_subs1.ui.tv_show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowResponses
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    /**
     * Loader state
     */
    private val _state = MutableLiveData<LoaderState>()
    val state : LiveData<LoaderState> = _state

    /**
     * Network error
     */
    private val _networkError = MutableLiveData<Boolean>()
    val networkError : LiveData<Boolean> = _networkError

    /**
     * Error
     */
    private val _error = MutableLiveData<String?>()
    val error : LiveData<String?> = _error

    /**
     * Result from tv shows
     */
    private val _resultTvShowsFromApi = MutableLiveData<List<TvShowResponses>?>()
    val resultTvShowFromApi : LiveData<List<TvShowResponses>?> = _resultTvShowsFromApi

    init {
        getTvShowResultsFromApi()
    }

    fun getTvShowResultsFromApi() {
        _state.value = LoaderState.ShowLoading
        viewModelScope.launch {
            val result = tvShowUseCase.getDiscoverTvShows()
            _state.value = LoaderState.HideLoading
            when(result) {
                is ResultState.Success -> _resultTvShowsFromApi.postValue(result.data?.tvShowResponses)
                is ResultState.Error -> _error.postValue(result.error)
                is ResultState.NetworkError -> _networkError.postValue(true)
            }
        }
    }


}