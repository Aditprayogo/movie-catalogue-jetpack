package com.aditprayogo.bajp_subs1.di

import android.app.Application
import androidx.room.Room
import com.aditprayogo.bajp_subs1.data.local.database.MoviezDatabase
import com.aditprayogo.bajp_subs1.data.local.database.dao.MovieDao
import com.aditprayogo.bajp_subs1.data.local.database.dao.TvShowDao
import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.RetrofitMovieConfig
import com.aditprayogo.bajp_subs1.data.repository.movie.MovieRepository
import com.aditprayogo.bajp_subs1.data.repository.movie.MovieRepositoryImpl
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepository
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepositoryImpl
import com.aditprayogo.bajp_subs1.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provdesNetworkClient(): MovieServices =
        RetrofitMovieConfig
            .retrofitClient()
            .create(MovieServices::class.java)

    @Provides
    @Singleton
    fun providesMoviezDatabase(
        app : Application
    ) : MoviezDatabase {
        return Room.databaseBuilder(
            app,
            MoviezDatabase::class.java,
            Constant.databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun providesMovieDao(
        moviezDatabase: MoviezDatabase
    ) : MovieDao = moviezDatabase.movieDao()

    @Provides
    @Singleton
    fun providesTvShowDao(
        moviezDatabase: MoviezDatabase
    ) : TvShowDao = moviezDatabase.tvShowDao()

    @Provides
    @Singleton
    fun provideMovieRepository(
        movieServices: MovieServices,
        movieDao: MovieDao
    ) : MovieRepository = MovieRepositoryImpl(movieServices, movieDao)

    @Provides
    @Singleton
    fun provideTvShowRepository(
        movieServices: MovieServices,
        tvShowDao: TvShowDao
    ) : TvShowRepository = TvShowRepositoryImpl(movieServices, tvShowDao)


}