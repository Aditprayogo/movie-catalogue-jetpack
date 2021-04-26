package com.aditprayogo.bajp_subs1.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.databinding.FragmentMovieBinding
import com.aditprayogo.bajp_subs1.utils.setGone
import com.aditprayogo.bajp_subs1.utils.setVisible
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
            state.observe(viewLifecycleOwner, {
                handleStateLoading(it)
            })
            discoverMovie.observe(viewLifecycleOwner, {
                handleResultDiscoverMovie(it)
            })
            networkError.observe(viewLifecycleOwner, {
                handleNetworkError(it)
            })
            error.observe(viewLifecycleOwner, {
                it?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
            })
        }
    }

    private fun handleResultDiscoverMovie(movie: List<MovieResponses>) {
        movie.let {
            movieLists.clear()
            movieLists.addAll(movie)
            movieAdapter.setMoviesData(movieLists)
        }
    }

    private fun handleNetworkError(error: Boolean) {
        with(binding) {
            if (error) {
                baseLoadingShimmer.root.setVisible()
                rvMovie.setGone()
            } else {
                baseLoadingShimmer.root.setGone()
                rvMovie.setVisible()
            }
        }
    }

    private fun handleStateLoading(state: LoaderState) {
        with(binding) {
            if (state is LoaderState.ShowLoading) {
                baseLoadingShimmer.root.setVisible()
                rvMovie.setGone()
            } else {
                baseLoadingShimmer.root.setGone()
                rvMovie.setVisible()
            }
        }
    }

}