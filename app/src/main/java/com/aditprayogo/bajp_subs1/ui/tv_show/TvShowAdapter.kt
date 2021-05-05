package com.aditprayogo.bajp_subs1.ui.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowResponses
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding

/**
 * Created by Aditiya Prayogo.
 */
class TvShowAdapter : RecyclerView.Adapter<TvShowViewHolder>() {

    private var listTvShow: MutableList<TvShowResponses> = mutableListOf()

    fun setTvShowsData(tvShow: MutableList<TvShowResponses>) {
        tvShow.let {
            this.listTvShow = tvShow
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowViewHolder {
        val itemRowMovieBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemRowMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        listTvShow[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listTvShow.size
}