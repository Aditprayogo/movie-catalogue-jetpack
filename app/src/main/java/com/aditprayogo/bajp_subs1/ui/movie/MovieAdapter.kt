package com.aditprayogo.bajp_subs1.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.R
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
}