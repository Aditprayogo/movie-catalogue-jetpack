package com.aditprayogo.bajp_subs1.ui.favorite_movie

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.R
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding
import com.aditprayogo.bajp_subs1.ui.detail.DetailActivity
import com.aditprayogo.bajp_subs1.utils.load

/**
 * Created by Aditiya Prayogo.
 */
class FavoriteMovieViewHolder(private val binding: ItemRowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: MovieEntity) {
        with(binding) {

            imgMovie.load(data.posterPath)
            rattingBar.rating = (data.voteAverage?.toFloat()?.div(2)!!)
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