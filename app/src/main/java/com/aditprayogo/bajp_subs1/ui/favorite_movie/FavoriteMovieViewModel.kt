package com.aditprayogo.bajp_subs1.ui.favorite_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.aditprayogo.bajp_subs1.core.state.ResultState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
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

    private val _error = MutableLiveData<String?>()
    val error = _error

    lateinit var resultMovieFromDb: LiveData<PagedList<MovieEntity>>

    init {
        getMovieFavorite()
    }

    fun getMovieFavorite() {
        viewModelScope.launch {
            val result = movieUseCase.getMoviesFavorite()
            when (result) {
                is ResultState.Success -> {
                    val config = PagedList.Config.Builder().apply {
                        setEnablePlaceholders(false)
                        setInitialLoadSizeHint(4)
                        setPageSize(4)
                    }.build()

                    val transformationData = LivePagedListBuilder(result.data, config).build()
                    resultMovieFromDb = transformationData
                }
                is ResultState.Error -> _error.postValue(result.error)
            }
        }
    }

}
