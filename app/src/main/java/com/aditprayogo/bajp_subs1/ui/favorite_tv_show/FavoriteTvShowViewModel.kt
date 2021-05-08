package com.aditprayogo.bajp_subs1.ui.favorite_tv_show

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Aditiya Prayogo.
 */
@HiltViewModel
class FavoriteTvShowViewModel @Inject constructor(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {

    /**
     * Error request
     */
    private val _error = MutableLiveData<String?>()
    val error = _error

    /**
     * Discover movie Result
     */
    private val _resultTvShowFromDb = MutableLiveData<List<TvShowEntity>>()
    val resultTvShowFromDb : LiveData<List<TvShowEntity>> = _resultTvShowFromDb

    init {
        getTvShowFavorite()
    }

    /**
     * get discover movie
     */
    fun getTvShowFavorite() {
        viewModelScope.launch {
            when ( val result = tvShowUseCase.getTvShowFavorite()) {
                is ResultState.Success -> _resultTvShowFromDb.postValue(result.data)
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

}