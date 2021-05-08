package com.aditprayogo.bajp_subs1.ui.favorite_tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding

/**
 * Created by Aditiya Prayogo.
 */
class FavoriteTvShowAdapter : RecyclerView.Adapter<FavoriteTvShowViewHolder>() {

    private var tvShows = mutableListOf<TvShowEntity>()

    fun setTvShow(data: MutableList<TvShowEntity>) {
        this.tvShows = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        val itemRowMovieBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvShowViewHolder(itemRowMovieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size
}