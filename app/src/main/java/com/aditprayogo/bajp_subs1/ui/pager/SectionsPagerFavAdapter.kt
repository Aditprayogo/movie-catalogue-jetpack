package com.aditprayogo.bajp_subs1.ui.pager

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.ui.favorite_movie.FavoriteMovieFragment
import com.aditprayogo.bajp_subs1.ui.favorite_tv_show.FavoriteTvShowFragment
import com.aditprayogo.bajp_subs1.ui.movie.MovieFragment
import com.aditprayogo.bajp_subs1.ui.tv_show.TvShowFragment

/**
 * Created by Aditiya Prayogo.
 */
class SectionsPagerFavAdapter(
    private val context: Context,
    fm : FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(R.string.movieTitle, R.string.tvShowTitle)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLE[position])
    }

    override fun getCount(): Int = TAB_TITLE.size

    override fun getItem(position: Int): Fragment =
        when(position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }

}