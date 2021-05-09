package com.aditprayogo.bajp_subs1.ui.favorite_movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.databinding.FragmentFavoriteMovieBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    private val binding : FragmentFavoriteMovieBinding by lazy {
        FragmentFavoriteMovieBinding.inflate(layoutInflater)
    }

    private val favoriteMovieAdapter : FavoriteMovieAdapter by lazy {
        FavoriteMovieAdapter()
    }

    private val favoriteMovieViewModel by viewModels<FavoriteMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvMovieFav.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favoriteMovieAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        with(favoriteMovieViewModel) {
            getFavoriteMovies().observe(viewLifecycleOwner, { movieData ->
                favoriteMovieAdapter.submitList(movieData)
            })
        }
    }

    private fun initObservers() {
        with(favoriteMovieViewModel) {
            getFavoriteMovies().observe(viewLifecycleOwner, { movieData ->
                favoriteMovieAdapter.submitList(movieData)
            })
        }
    }

}