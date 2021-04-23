package com.aditprayogo.bajp_subs1.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.bajp_subs1.data.local.Movie
import com.aditprayogo.bajp_subs1.databinding.ActivityDetailBinding
import com.aditprayogo.bajp_subs1.utils.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupData()
    }

    private fun setupData() {
        val extras = intent.extras

        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
        }

        extras?.let {
            val movieData = it.getString(EXTRA_MOVIE_ID)
            movieData?.let {
                detailViewModel.setSelectedMovie(movieData)
                populateMovie(detailViewModel.getMovies())
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateMovie(movie: Movie) {
        with(binding) {
            imgDetailMovie.load(movie.image)
            txtOverview.text = movie.overview
            txtGenre.text = movie.genre
            txtDateOfRelease.text = movie.dateOfRealese
            txtDuration.text = movie.duration
            txtTitleMovie.text = movie.title
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }
}