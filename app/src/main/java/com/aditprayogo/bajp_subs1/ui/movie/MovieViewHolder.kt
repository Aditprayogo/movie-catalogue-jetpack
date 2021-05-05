package com.aditprayogo.bajp_subs1.ui.movie

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding
import com.aditprayogo.bajp_subs1.ui.detail.DetailActivity
import com.aditprayogo.bajp_subs1.utils.load

/**
 * Created by Aditiya Prayogo.
 */
class MovieViewHolder(private val binding: ItemRowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(data: MovieResponses) {
        with(binding) {

            imgMovie.load(data.getPosterMovieImage())
            rattingBar.rating = (data.voteAverage.toFloat().div(2))
            txtRattingBar.text = data.voteAverage.toString()
            txtTitle.text = data.title
            txtDateOfRelease.text = data.releaseDate

            with(itemView) {
                setOnClickListener {
                    context.startActivity(
                        Intent(
                            context, DetailActivity::class.java
                        ).apply {
                            putExtra(DetailActivity.EXTRA_MOVIE_ID, data.id.toString())
                            putExtra(DetailActivity.TYPE, context.getString(R.string.movieType))
                        }
                    )
                }
            }

        }
    }
}