package com.aditprayogo.bajp_subs1.data.local.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity

/**
 * Created by Aditiya Prayogo.
 */
@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies() : DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movies WHERE id =:id")
    suspend fun getMovieFavById(id : Int) : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieToDb(movie : MovieEntity)

    @Delete
    suspend fun deleteMovieFromDb(movie: MovieEntity)

}