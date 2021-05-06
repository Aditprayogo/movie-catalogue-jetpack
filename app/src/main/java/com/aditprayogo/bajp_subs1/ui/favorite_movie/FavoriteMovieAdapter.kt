package com.aditprayogo.bajp_subs1.ui.favorite_movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding

/**
 * Created by Aditiya Prayogo.
 */
class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieViewHolder>() {

    private var movies = mutableListOf<MovieEntity>()

    fun setFavMovies(data : MutableList<MovieEntity>) {
        this.movies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val itemRowMovieBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(itemRowMovieBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}