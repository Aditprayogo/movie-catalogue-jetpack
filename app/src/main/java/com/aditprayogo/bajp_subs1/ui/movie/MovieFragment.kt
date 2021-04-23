package com.aditprayogo.bajp_subs1.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.databinding.FragmentMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val binding: FragmentMovieBinding by lazy {
        FragmentMovieBinding.inflate(layoutInflater)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    private val movieViewModel: MovieViewModel by viewModels()

    private var movieLists = mutableListOf<MovieResponses>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }
    }

    private fun initObservers() {
        with(movieViewModel) {
            discoverMovie.observe(viewLifecycleOwner, {
                handleResultDiscoverMovie(it)
            })
        }
    }

    private fun handleResultDiscoverMovie(movie: List<MovieResponses>?) {
        movie?.let {
            movieLists.clear()
            movieLists.addAll(movie)
            movieAdapter.setMoviesData(movieLists)
        }
    }


}