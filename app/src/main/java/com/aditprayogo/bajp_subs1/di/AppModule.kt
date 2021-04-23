package com.aditprayogo.bajp_subs1.di

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.RetrofitMovieConfig
import com.aditprayogo.bajp_subs1.data.repository.MovieRepository
import com.aditprayogo.bajp_subs1.data.repository.MovieRepositoryImpl
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
    fun provideMovieRepository(
        movieServices: MovieServices
    ) : MovieRepository = MovieRepositoryImpl(movieServices)

}