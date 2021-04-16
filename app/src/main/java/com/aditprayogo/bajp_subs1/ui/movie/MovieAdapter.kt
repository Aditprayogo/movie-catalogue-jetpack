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
import com.aditprayogo.bajp_subs1.data.Movie
import com.aditprayogo.bajp_subs1.databinding.ItemRowMovieBinding
import com.aditprayogo.bajp_subs1.ui.detail.DetailActivity
import com.aditprayogo.bajp_subs1.utils.load

/**
 * Created by Aditiya Prayogo.
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovies: ArrayList<Movie> = ArrayList()

    fun setMoviesData(movies: List<Movie>?) {
        movies?.let {
            this.listMovies.clear()
            this.listMovies.addAll(movies)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemRowMovieBinding =
            ItemRowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemRowMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MovieViewHolder(private val binding: ItemRowMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding) {
                imgMovie.load(data.image)
                txtTitle.text = data.title
                txtDateOfRelease.text = data.dateOfRealese
                txtGenre.text = data.genre

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