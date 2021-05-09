package com.aditprayogo.bajp_subs1.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.databinding.ActivityMainBinding
import com.aditprayogo.bajp_subs1.ui.favorite.FavoriteActivity
import com.aditprayogo.bajp_subs1.ui.pager.SectionsPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViewAdapter()
    }

    private fun setupViewAdapter() {
        val sectionAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_favorite) startActivity(
            Intent(
                this,
                FavoriteActivity::class.java
            )
        )
        return super.onOptionsItemSelected(item)
    }


}