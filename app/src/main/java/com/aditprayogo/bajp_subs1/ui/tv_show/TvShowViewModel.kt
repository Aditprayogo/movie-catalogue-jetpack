package com.aditprayogo.bajp_subs1.ui.tv_show

import androidx.lifecycle.ViewModel
import com.aditprayogo.bajp_subs1.utils.MovieDummy

/**
 * Created by Aditiya Prayogo.
 */
class TvShowViewModel : ViewModel() {

    fun getTvShow() = MovieDummy.getTvShow()

}