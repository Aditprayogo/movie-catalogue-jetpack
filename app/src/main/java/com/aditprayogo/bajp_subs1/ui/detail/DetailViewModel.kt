package com.aditprayogo.bajp_subs1.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import com.aditprayogo.bajp_subs1.utils.EspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel(), DetailViewModelContract {

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
     * Insert movie to db status
     */
    private val _resultInsertMovieToDb = MutableLiveData<Boolean>()
    val resultInsertMovieToDb: LiveData<Boolean> = _resultInsertMovieToDb

    /**
     * Delete movie to db status
     */
    private val _resultDeleteMovieFromDb = MutableLiveData<Boolean>()
    val resultDeleteMovieFromDb: LiveData<Boolean> = _resultDeleteMovieFromDb

    /**
     * result movie from db
     */
    private val _resultMovieFavFromDb = MutableLiveData<List<MovieEntity>>()
    val resultMovieFavFromDb: LiveData<List<MovieEntity>> = _resultMovieFavFromDb

    /**
     * Insert tv show to db status
     */
    private val _resultInsertTvShowToDb = MutableLiveData<Boolean>()
    val resultInsertTvShowToDb: LiveData<Boolean> = _resultInsertTvShowToDb

    /**
     * Delete tv show to db status
     */
    private val _resultDeleteTvShowFromDb = MutableLiveData<Boolean>()
    val resultDeleteTvShowFromDb: LiveData<Boolean> = _resultDeleteTvShowFromDb

    /**
     * result tv show from db
     */
    private val _resultTvShowFavFromDb = MutableLiveData<List<TvShowEntity>>()
    val resultTvShowFavFromDb: LiveData<List<TvShowEntity>> = _resultTvShowFavFromDb

    /**
     * get movie detail
     */
    override fun getMovieDetailResult(id: String) {
        _state.value = LoaderState.ShowLoading
        EspressoIdlingResource.increment()

        viewModelScope.launch {
            val result = movieUseCase.getDetailMovie(id)
            _state.value = LoaderState.HideLoading
            EspressoIdlingResource.decrement()

            when (result) {
                is ResultState.Success -> _movieDetailResultFromApi.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
                is ResultState.NetworkError -> _networkError.postValue(true)
            }
        }
    }

    /**
     * get tv show detail
     */
    override fun getTvShowDetailResult(id: String) {
        _state.value = LoaderState.ShowLoading
        EspressoIdlingResource.increment()

        viewModelScope.launch {
            val result = tvShowUseCase.getDetailTvShow(id)
            _state.value = LoaderState.HideLoading
            EspressoIdlingResource.decrement()

            when (result) {
                is ResultState.Success -> _tvShowDetailResultFromApi.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
                is ResultState.NetworkError -> _networkError.postValue(true)
            }
        }
    }


    /**
     * get movie from db
     */
    override fun getFavMovieById(id: String) {
        viewModelScope.launch {
            when (val result = movieUseCase.getFavMovieById(id.toInt())) {
                is ResultState.Success -> _resultMovieFavFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

    /**
     * Insert Movie to Db
     */
    override fun insertMovieToDb(movieEntity: MovieEntity) {
        viewModelScope.launch {
            try {
                movieUseCase.insertMovieToDb(movieEntity)
                _resultInsertMovieToDb.postValue(true)
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    /**
     * Delete movie from db
     */
    override fun deleteMovieFromDb(movieEntity: MovieEntity) {
        viewModelScope.launch {
            try {
                movieUseCase.deleteMovieFromDb(movieEntity)
                _resultDeleteMovieFromDb.postValue(true)
            } catch (e: Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    /**
     * get favorite tv show from db
     */
    override fun getFavTvShowById(id: String) {
        viewModelScope.launch {
            when (val result = tvShowUseCase.getTvShowFavById(id.toInt())) {
                is ResultState.Success -> _resultTvShowFavFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

    override fun insertTvShowToDb(tvShowEntity: TvShowEntity) {
        viewModelScope.launch {
            try {
                tvShowUseCase.insertTvShowToDb(tvShowEntity)
                _resultInsertTvShowToDb.postValue(true)
            } catch (e : Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }

    override fun deleteTvShowFromDb(tvShowEntity: TvShowEntity) {
        viewModelScope.launch {
            try {
                tvShowUseCase.deleteTvShowFromDb(tvShowEntity)
                _resultDeleteTvShowFromDb.postValue(true)
            } catch (e : Exception) {
                _error.postValue(e.localizedMessage)
            }
        }
    }


}