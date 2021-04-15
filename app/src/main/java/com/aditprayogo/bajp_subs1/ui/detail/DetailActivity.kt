package com.aditprayogo.bajp_subs1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.data.Movie
import com.aditprayogo.bajp_subs1.databinding.ActivityDetailBinding
import com.aditprayogo.bajp_subs1.utils.load

class DetailActivity : AppCompatActivity() {

    private val binding : ActivityDetailBinding by lazy {
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

        extras?.let {
            val movieData = it.getString(EXTRA_MOVIE_ID)
            movieData?.let {
                detailViewModel.setSelectedMovie(movieData)
                populateMovie(detailViewModel.getMovies())
            }
        }
    }

    private fun populateMovie(movie : Movie) {
        with(binding) {
            imgDetailMovie.load(movie.image)
        }
    }

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
    }
}