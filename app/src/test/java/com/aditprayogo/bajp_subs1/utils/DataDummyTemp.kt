package com.aditprayogo.bajp_subs1.utils

import com.aditprayogo.bajp_subs1.data.remote.responses.MovieDiscoverResponses
import com.aditprayogo.bajp_subs1.data.remote.responses.MovieResponses
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowDiscoverResponses
import com.aditprayogo.bajp_subs1.data.remote.responses.TvShowResponses

/**
 * Created by Aditiya Prayogo.
 */
object DataDummyTemp {

    val discoverMovieResponses = MovieDiscoverResponses(
        page = 1,
        listOf(
            MovieResponses(
                id = 1,
                title = "movieTitle",
                voteAverage = 0.0,
                releaseDate = "releaseDate",
                overview = "overView",
                backdropPath = "backdropPath"
            ),
            MovieResponses(
                id = 2,
                title = "movieTitle",
                voteAverage = 0.0,
                releaseDate = "releaseDate",
                overview = "overView",
                backdropPath = "backdropPath"
            ),
        )
    )

    val discoverTvShowResponses = TvShowDiscoverResponses(
        page = 1,
        listOf(
            TvShowResponses(
                id = 1,
                name = "tvShowTitle",
                voteAverage = 0.0,
                firstAirDate = "releaseDate",
                backdropPath = "backdropPath"
            ),
            TvShowResponses(
                id = 2,
                name = "tvShowTitle",
                voteAverage = 0.0,
                firstAirDate = "releaseDate",
                backdropPath = "backdropPath"
            ),
        )
    )

    fun generateMovieTemp() : List<MovieResponses> {
        return listOf(
            MovieResponses(
                id = 1,
                title = "movieTitle",
                voteAverage = 0.0,
                releaseDate = "releaseDate",
                overview = "overView",
                backdropPath = "backdropPath"
            ),
            MovieResponses(
                id = 2,
                title = "movieTitle",
                voteAverage = 0.0,
                releaseDate = "releaseDate",
                overview = "overView",
                backdropPath = "backdropPath"
            ),
            MovieResponses(
                id = 3,
                title = "movieTitle",
                voteAverage = 0.0,
                releaseDate = "releaseDate",
                overview = "overView",
                backdropPath = "backdropPath"
            )
        )
    }

    fun generateTvShowsTemp() : List<TvShowResponses> {
        return listOf(
            TvShowResponses(
                id = 1,
                name = "tvShowTitle",
                voteAverage = 0.0,
                firstAirDate = "releaseDate",
                backdropPath = "backdropPath"
            ),
            TvShowResponses(
                id = 2,
                name = "tvShowTitle",
                voteAverage = 0.0,
                firstAirDate = "releaseDate",
                backdropPath = "backdropPath"
            ),
            TvShowResponses(
                id = 3,
                name = "tvShowTitle",
                voteAverage = 0.0,
                firstAirDate = "releaseDate",
                backdropPath = "backdropPath"
            ),
        )
    }
}