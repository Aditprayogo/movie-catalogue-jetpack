package com.aditprayogo.bajp_subs1.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding

/**
 * Created by Aditiya Prayogo.
 */
class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

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


}