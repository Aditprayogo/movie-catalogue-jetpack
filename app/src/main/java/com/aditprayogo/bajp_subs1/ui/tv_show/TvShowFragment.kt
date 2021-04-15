package com.aditprayogo.bajp_subs1.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.bajp_subs1.databinding.FragmentTvShowBinding

class TvShowFragment : Fragment() {

    private val binding : FragmentTvShowBinding by lazy {
        FragmentTvShowBinding.inflate(layoutInflater)
    }

    private val tvShowViewModel : TvShowViewModel by viewModels()
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    private fun setupData() {
        val tvShows = tvShowViewModel.getTvShow()
        tvShowAdapter = TvShowAdapter()
        tvShowAdapter.setTvShowsData(tvShows)

        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

}