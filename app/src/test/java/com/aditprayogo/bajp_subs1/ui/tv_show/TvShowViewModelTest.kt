package com.aditprayogo.bajp_subs1.ui.tv_show

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by Aditiya Prayogo.
 */
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun `get all tv show data and returns success`() {
        val tvShows = viewModel.getTvShow()

        assertThat(tvShows).isNotNull()
        assertThat(tvShows).isEqualTo(10)
    }

}