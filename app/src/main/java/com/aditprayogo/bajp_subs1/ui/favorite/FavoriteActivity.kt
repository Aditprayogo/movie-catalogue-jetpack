package com.aditprayogo.bajp_subs1.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.databinding.ActivityFavoriteBinding
import com.aditprayogo.bajp_subs1.ui.pager.SectionsPagerAdapter
import com.aditprayogo.bajp_subs1.ui.pager.SectionsPagerFavAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val binding : ActivityFavoriteBinding by lazy {
        ActivityFavoriteBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initToolbar()
        setupViewAdapter()
    }

    private fun initToolbar() {
        supportActionBar?.apply {
            title = getString(R.string.favorite_title)
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupViewAdapter() {
        val sectionAdapter = SectionsPagerFavAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}