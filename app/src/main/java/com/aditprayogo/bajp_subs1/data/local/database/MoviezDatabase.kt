package com.aditprayogo.bajp_subs1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aditprayogo.bajp_subs1.data.local.database.dao.MovieDao
import com.aditprayogo.bajp_subs1.data.local.database.dao.TvShowDao
import com.aditprayogo.bajp_subs1.data.local.database.entity.MovieEntity
import com.aditprayogo.bajp_subs1.data.local.database.entity.TvShowEntity

/**
 * Created by Aditiya Prayogo.
 */
@Database(
    entities = [MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoviezDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao

}