package com.aditprayogo.bajp_subs1.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDetailResponse
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDetailResponse
import com.aditprayogo.bajp_subs1.databinding.ActivityDetailBinding
import com.aditprayogo.bajp_subs1.ui.favorite.FavoriteActivity
import com.aditprayogo.bajp_subs1.utils.load
import com.aditprayogo.bajp_subs1.utils.setGone
import com.aditprayogo.bajp_subs1.utils.setVisible
import com.aditprayogo.bajp_subs1.utils.toast
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

    private var favoriteActive = false

    private var movieEntity: MovieEntity? = null

    private var tvShowEntity: TvShowEntity? = null

    private var movieDetailResponse: MovieDetailResponse? = null

    private var tvShowDetailResponse: TvShowDetailResponse? = null

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntentData()
        fetchData()
        initObservers()
        initToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) startActivity(Intent(this, FavoriteActivity::class.java))
        return super.onOptionsItemSelected(item)
    }

    private fun fetchData() {
        when (type) {
            getString(R.string.tvShowType) -> tvShowId?.let {
                detailViewModel.getTvShowDetailResult(it)
                detailViewModel.getFavTvShowById(it)
            }
            getString(R.string.movieType) -> movieId?.let {
                detailViewModel.getMovieDetailResult(it)
                detailViewModel.getFavMovieById(it)
            }
        }
    }

    private fun initToolbar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        binding.favButton.setOnClickListener {
            setFavoriteMovie()
        }
    }

    private fun setFavoriteMovie() {
        when(type) {
            getString(R.string.movieType) -> {
                if (favoriteActive) {
                    movieEntity?.let { detailViewModel.deleteMovieFromDb(it) }
                } else {
                    val movieFavorite = movieId?.let {
                        MovieEntity(
                            id = it.toInt(),
                            posterPath = movieDetailResponse?.generateMoviePosterImage(),
                            overview = movieDetailResponse?.overview,
                            releaseDate = movieDetailResponse?.releaseDate,
                            status = movieDetailResponse?.status,
                            title = movieDetailResponse?.title,
                            voteAverage = movieDetailResponse?.voteAverage,
                            genres = movieDetailResponse?.genres?.map { genre -> genre.name }.toString()
                        )
                    }
                    movieFavorite?.let { detailViewModel.insertMovieToDb(it) }
                }
            }
            getString(R.string.tvShowType) -> {
                if (favoriteActive) {
                    tvShowEntity?.let { detailViewModel.deleteTvShowFromDb(it) }
                } else {
                    val tvShowFavorite = tvShowId?.let {
                        TvShowEntity(
                            id = it.toInt(),
                            posterPath = tvShowDetailResponse?.generateImageTvDetail(),
                            overview = tvShowDetailResponse?.overview,
                            firstAirDate = tvShowDetailResponse?.firstAirDate,
                            status = tvShowDetailResponse?.status,
                            title = tvShowDetailResponse?.name,
                            voteAverage = tvShowDetailResponse?.voteAverage,
                            genres = tvShowDetailResponse?.genres?.map { it.name }.toString(),
                        )
                    }
                    tvShowFavorite?.let { detailViewModel.insertTvShowToDb(it) }
                }
            }
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
            resultMovieFavFromDb.observe(this@DetailActivity, {
                handleMovieFavFromDb(it)
            })
            resultInsertMovieToDb.observe(this@DetailActivity, { status ->
                handleRInsertMovieToDb(status)
            })
            resultDeleteMovieFromDb.observe(this@DetailActivity, { status ->
                handleDeleteMovieFromDb(status)
            })
            resultTvShowFavFromDb.observe(this@DetailActivity, {
                handleResultTvShowFavorite(it)
            })
            resultInsertTvShowToDb.observe(this@DetailActivity, { status ->
                handleInsertTvShowToDb(status)
            })
            resultDeleteTvShowFromDb.observe(this@DetailActivity, { status ->
                handleDeleteTvShowFromDb(status)
            })
        }
    }

    private fun handleDeleteTvShowFromDb(status: Boolean) {
        if (status) tvShowId?.let { detailViewModel.getFavTvShowById(it) }
        toast("Successful UnFavorite Tv Serries")
    }

    private fun handleInsertTvShowToDb(status: Boolean) {
        if (status) tvShowId?.let { detailViewModel.getFavTvShowById(it) }
        toast("Successful added to favorite")
    }

    private fun handleDeleteMovieFromDb(status: Boolean) {
        if (status) movieId?.let { detailViewModel.getFavMovieById(it) }
        toast("Successful UnFavorite Movie")
    }

    private fun handleRInsertMovieToDb(status: Boolean) {
        if (status) movieId?.let { detailViewModel.getFavMovieById(it) }
        toast("Successful added to favorite")
    }

    private fun handleResultTvShowFavorite(data: List<TvShowEntity>) {
        if (data.isEmpty()) {
            favoriteActive = false
            val icon = R.drawable.ic_baseline_favorite_border_24
            binding.favButton.setImageResource(icon)
        } else {
            tvShowEntity = data.first()
            favoriteActive = true
            val icon = R.drawable.ic_baseline_favorite_24
            binding.favButton.setImageResource(icon)
        }
    }

    private fun handleMovieFavFromDb(data: List<MovieEntity>) {
        if (data.isEmpty()) {
            favoriteActive = false
            val icon = R.drawable.ic_baseline_favorite_border_24
            binding.favButton.setImageResource(icon)
        } else {
            movieEntity = data.first()
            favoriteActive = true
            val icon = R.drawable.ic_baseline_favorite_24
            binding.favButton.setImageResource(icon)
        }
    }

    private fun handleTvShowResultFromApi(tvShowDetail: TvShowDetailResponse) {
        tvShowDetailResponse = tvShowDetail

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
        movieDetailResponse = movieDetail

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
        error?.let { toast(it) }
    }

    private fun handleLoaderState(loaderState: LoaderState) {
        with(binding) {
            if (loaderState is LoaderState.ShowLoading) {
                progressBar.setVisible()
                favButton.setGone()
            } else {
                progressBar.setGone()
                favButton.setVisible()
            }
        }

    }

    private fun handleNetworkError(status: Boolean) {
        if (status) toast("Please Retry your connection")
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