package com.aditprayogo.bajp_subs1.ui.movie

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
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding
import com.aditprayogo.bajp_subs1.ui.detail.DetailActivity
import com.aditprayogo.bajp_subs1.utils.load

/**
 * Created by Aditiya Prayogo.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieLists = mutableListOf<MovieResponses>()

    fun setMoviesData(movies: MutableList<MovieResponses>?) {
        movies?.let {
            this.movieLists = movies
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemRowMovieBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemRowMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieLists[position])
    }

    override fun getItemCount(): Int = movieLists.size

    inner class MovieViewHolder(private val binding: ItemRowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieResponses) {
            with(binding) {

                imgMovie.load(data.getPosterMovieImage())
                rattingBar.rating = (data.voteAverage?.toFloat()?.div(2)) ?: 0f
                txtRattingBar.text = data.voteAverage?.toString()
                txtTitle.text = data.title
                txtDateOfRelease.text = data.releaseDate

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