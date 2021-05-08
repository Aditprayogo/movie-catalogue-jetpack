package com.aditprayogo.bajp_subs1.utils

import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity
import com.aditprayogo.bajp_subs1.data.remote.responses.*

/**
 * Created by Aditiya Prayogo.
 */
object DataDummyTemp {

    val favoriteMovie = MovieEntity(
        id = 1,
        posterPath = "posterPath",
        overview = "overview",
        releaseDate = "releaseDate",
        status = "status",
        title = "title",
        voteAverage = 2.0,
        genres = "genres"
    )

    val favoriteTvShow = TvShowEntity(
        id = 1,
        posterPath = "posterPath",
        overview = "overview",
        firstAirDate  = "firstAirDate",
        status = "status",
        title = "title",
        voteAverage = 2.0,
        genres = "genres"
    )

    val listFavoriteMovie = listOf(
        MovieEntity(
            id = 1,
            posterPath = "posterPath",
            overview = "overview",
            releaseDate = "releaseDate",
            status = "status",
            title = "title",
            voteAverage = 2.0,
            genres = "genres"
        ),
        MovieEntity(
            id = 2,
            posterPath = "posterPath",
            overview = "overview",
            releaseDate = "releaseDate",
            status = "status",
            title = "title",
            voteAverage = 2.0,
            genres = "genres"
        )
    )

    val listFavoriteTvShow = listOf(
        TvShowEntity(
            id = 1,
            posterPath = "posterPath",
            overview = "overview",
            firstAirDate  = "firstAirDate",
            status = "status",
            title = "title",
            voteAverage = 2.0,
            genres = "genres"
        ),
        TvShowEntity(
            id = 1,
            posterPath = "posterPath",
            overview = "overview",
            firstAirDate  = "firstAirDate",
            status = "status",
            title = "title",
            voteAverage = 2.0,
            genres = "genres"
        )
    )

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

    fun generateMovieTemp(): List<MovieResponses> {
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

    fun generateTvShowsTemp(): List<TvShowResponses> {
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

    val detailMovie = MovieDetailResponse(
        id = 1,
        genres = listOf(
            Genre(id = 1, name = "genre1"),
            Genre(id = 2, name = "genre2"),
        ),
        overview = "overview",
        posterPath = "posterPath",
        releaseDate = "releaseDate",
        status = "status",
        title = "Title",
        voteAverage = 0.0

    )

    val detailTvShow = TvShowDetailResponse(
        id = 1,
        genres = listOf(
            GenreX(id = 1, name = "genre1"),
            GenreX(id = 2, name = "genre2"),
        ),
        overview = "overview",
        posterPath = "posterPath",
        firstAirDate = "firstAirDate",
        status = "status",
        name = "name",
        voteAverage = 0.0

    )
}