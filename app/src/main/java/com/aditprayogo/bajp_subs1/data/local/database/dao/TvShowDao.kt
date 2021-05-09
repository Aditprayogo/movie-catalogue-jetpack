package com.aditprayogo.bajp_subs1.data.local.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity

/**
 * Created by Aditiya Prayogo.
 */
@Dao
interface TvShowDao {

    @Query("SELECT * FROM tvShow")
    fun getAllTvShow() : DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvShow WHERE id =:id")
    suspend fun getTvShowById(id : Int) : List<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowToDb(tvShowEntity: TvShowEntity)

    @Delete
    suspend fun deleteTvShowFromDb(tvShowEntity: TvShowEntity)

}