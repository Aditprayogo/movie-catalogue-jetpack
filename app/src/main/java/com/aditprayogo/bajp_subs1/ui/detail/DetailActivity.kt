package com.aditprayogo.bajp_subs1.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.databinding.ActivityDetailBinding
import com.aditprayogo.bajp_subs1.utils.load
import com.aditprayogo.bajp_subs1.utils.setGone
import com.aditprayogo.bajp_subs1.utils.setVisible
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private var movieId: String? = null
    private var tvShowId: String? = null
    private var type: String? = null

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolbar()
        handleIntentData()
        fetchData()
        initObservers()
    }

    private fun initToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initObservers() {
        with(detailViewModel) {
            state.observe(this@DetailActivity, {
                handleLoaderState(it)
            })
            error.observe(this@DetailActivity, {
                handleErrorResult(it)
            })
            networkError.observe(this@DetailActivity, {
                handleNetworkError(it)
            })
            movieDetailResultFromApi.observe(this@DetailActivity, {
                handleMovieResultFromApi(it)
            })
            tvShowDetailResultFromApi.observe(this@DetailActivity, {
                handleTvShowResultFromApi(it)
            })
        }
    }

    private fun handleTvShowResultFromApi(tvShowDetail: TvShowDetailResponse) {
        with(binding) {
            imgDetailMovie.load(tvShowDetail.generateImageTvDetail())
            supportActionBar?.title = tvShowDetail.name
            txtDateOfRelease.text = tvShowDetail.firstAirDate
            txtStatus.text = tvShowDetail.status
            chipGroup.apply {
                for (genre in tvShowDetail.genres) {
                    val chip = Chip(this@DetailActivity)
                    chip.text = genre.name
                    chip.chipBackgroundColor = getColorStateList(R.color.bluePrimary)
                    chip.setTextColor(resources.getColor(R.color.white))
                    addView(chip)
                }
            }
            rattingBar.rating = (tvShowDetail.voteAverage.toFloat()).div(2)
            txtOverview.text = tvShowDetail.overview
        }
    }

    private fun handleMovieResultFromApi(movieDetail: MovieDetailResponse) {
        with(binding) {
            imgDetailMovie.load(movieDetail.generateMoviePosterImage())
            supportActionBar?.title = movieDetail.title
            txtDateOfRelease.text = movieDetail.releaseDate
            txtStatus.text = movieDetail.status
            chipGroup.apply {
                for (genre in movieDetail.genres) {
                    val chip = Chip(this@DetailActivity)
                    chip.text = genre.name
                    chip.chipBackgroundColor = getColorStateList(R.color.bluePrimary)
                    chip.setTextColor(resources.getColor(R.color.white))
                    addView(chip)
                }
            }
            rattingBar.rating = (movieDetail.voteAverage.toFloat()).div(2)
            txtOverview.text = movieDetail.overview
        }
    }

    private fun handleErrorResult(error: String?) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun handleLoaderState(loaderState: LoaderState) {
        with(binding) {
            if (loaderState is LoaderState.ShowLoading) {
                progressBar.setVisible()
            } else {
                progressBar.setGone()
            }
        }

    }

    private fun handleNetworkError(status: Boolean) {
        if (status) {
            Toast.makeText(this, "Please Retry your connection", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchData() {
        when (type) {
            getString(R.string.tvShowType) -> tvShowId?.let {
                detailViewModel.getTvShowDetailResult(it)
            }
            getString(R.string.movieType) -> movieId?.let {
                detailViewModel.getMovieDetailResult(it)
            }
        }
    }

    private fun handleIntentData() {
        movieId = intent.getStringExtra(EXTRA_MOVIE_ID)
        tvShowId = intent.getStringExtra(EXTRA_TV_SHOW_ID)
        type = intent.getStringExtra(TYPE)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        const val EXTRA_TV_SHOW_ID = "extra_movie_id"
        const val TYPE = "type"
    }
}