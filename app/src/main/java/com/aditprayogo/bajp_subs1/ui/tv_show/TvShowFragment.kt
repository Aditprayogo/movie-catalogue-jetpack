package com.aditprayogo.bajp_subs1.ui.tv_show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditprayogo.bajp_subs1.core.state.LoaderState
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowResponses
import com.aditprayogo.bajp_subs1.databinding.FragmentTvShowBinding
import com.aditprayogo.bajp_subs1.utils.setGone
import com.aditprayogo.bajp_subs1.utils.setVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val binding : FragmentTvShowBinding by lazy {
        FragmentTvShowBinding.inflate(layoutInflater)
    }

    private val tvShowViewModel : TvShowViewModel by viewModels()

    private val tvShowAdapter : TvShowAdapter by lazy {
        TvShowAdapter()
    }

    private var listsTvShow = mutableListOf<TvShowResponses>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOversvers()
        initRecycerView()
    }

    private fun initRecycerView() {
        binding.apply {
            rvTvShow.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvTvShow.adapter = tvShowAdapter
        }
    }

    private fun initOversvers() {
        with(tvShowViewModel) {
            state.observe(viewLifecycleOwner, {
                handleStateLoading(it)
            })
            networkError.observe(viewLifecycleOwner, {
                handleStateNetworkError(it)
            })
            resultTvShowFromApi.observe(viewLifecycleOwner, {
                handleStateTvShowResponse(it)
            })
            error.observe(viewLifecycleOwner, {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            })
        }
    }

    private fun handleStateTvShowResponse(tvShowData: List<TvShowResponses>?) {
        tvShowData?.let {
            listsTvShow.clear()
            listsTvShow.addAll(tvShowData)
            tvShowAdapter.setTvShowsData(listsTvShow)
        }
    }

    private fun handleStateNetworkError(status: Boolean) {
        with(binding) {
            if (status) {
                baseLoadingShimmer.root.setVisible()
                rvTvShow.setGone()
            } else {
                baseLoadingShimmer.root.setGone()
                rvTvShow.setVisible()
            }
        }
    }

    private fun handleStateLoading(loaderState: LoaderState) {
        with(binding) {
            if (loaderState is LoaderState.ShowLoading) {
                baseLoadingShimmer.root.setVisible()
                rvTvShow.setGone()
            } else {
                baseLoadingShimmer.root.setGone()
                rvTvShow.setVisible()
            }
        }
    }

}