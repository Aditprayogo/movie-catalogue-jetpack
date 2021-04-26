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
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        const val EXTRA_TV_SHOW_ID = "extra_movie_id"
    }
}