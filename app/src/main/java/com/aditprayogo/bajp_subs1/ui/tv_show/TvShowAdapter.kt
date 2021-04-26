package com.aditprayogo.bajp_subs1.ui.tv_show

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.data.local.Movie
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowResponses
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding
import com.aditprayogo.bajp_subs1.ui.detail.DetailActivity
import com.aditprayogo.bajp_subs1.utils.load

/**
 * Created by Aditiya Prayogo.
 */
class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

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
    ): TvShowAdapter.TvShowViewHolder {
        val itemRowMovieBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemRowMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        listTvShow[position]?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemRowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TvShowResponses) {
            with(binding) {

                imgMovie.load(data.getImagePoster())
                rattingBar.rating = (data.voteAverage?.toFloat()?.div(2)) ?: 0f
                txtRattingBar.text = data.voteAverage?.toString()
                txtTitle.text = data.name
                txtDateOfRelease.text = data.firstAirDate

                with(itemView) {
                    setOnClickListener {

                        val imagePair = Pair.create<View, String>(
                            imgMovie,
                            context.getString(R.string.img_detail_movie_transition)
                        )

                        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            context as Activity,
                            imagePair
                        )

                        context.startActivity(
                            Intent(
                                context, DetailActivity::class.java
                            ).apply {
                                putExtra(DetailActivity.EXTRA_MOVIE_ID, data.id)
                            },
                            options.toBundle()
                        )
                    }
                }

            }
        }
    }
}