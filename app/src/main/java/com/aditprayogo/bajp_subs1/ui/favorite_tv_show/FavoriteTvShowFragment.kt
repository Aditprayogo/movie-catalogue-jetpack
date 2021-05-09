package com.aditprayogo.bajp_subs1.ui.favorite_tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.databinding.FragmentFavoriteTvShowBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteTvShowFragment : Fragment() {

    private val binding : FragmentFavoriteTvShowBinding by lazy {
        FragmentFavoriteTvShowBinding.inflate(layoutInflater)
    }

    private val favoriteTvShowAdapter : FavoriteTvShowAdapter by lazy {
        FavoriteTvShowAdapter()
    }

    private val favoriteTvShowViewModel by viewModels<FavoriteTvShowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initObservers() {
        with(favoriteTvShowViewModel) {
            getTvShowFavorite().observe(viewLifecycleOwner, { tvShowData ->
                favoriteTvShowAdapter.submitList(tvShowData)
                favoriteTvShowAdapter.notifyDataSetChanged()
            })
        }
    }

    override fun onResume() {
        super.onResume()
        with(favoriteTvShowViewModel) {
            getTvShowFavorite().observe(viewLifecycleOwner, { tvShowData ->
                favoriteTvShowAdapter.submitList(tvShowData)
                favoriteTvShowAdapter.notifyDataSetChanged()
            })
        }
    }

    private fun initRecyclerView() {
        binding.rvTvShowFav.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = favoriteTvShowAdapter
        }
    }


}