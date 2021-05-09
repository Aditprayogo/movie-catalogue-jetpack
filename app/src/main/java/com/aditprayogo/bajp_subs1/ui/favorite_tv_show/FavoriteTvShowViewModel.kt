package com.aditprayogo.bajp_subs1.ui.favorite_tv_show

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun getTvShowFavorite() = tvShowUseCase.getTvShowFavorite()

}