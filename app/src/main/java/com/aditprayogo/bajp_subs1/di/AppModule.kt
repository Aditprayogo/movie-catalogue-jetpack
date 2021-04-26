package com.aditprayogo.bajp_subs1.di

import com.aditprayogo.bajp_subs1.data.remote.MovieServices
import com.aditprayogo.bajp_subs1.data.remote.RetrofitMovieConfig
import com.aditprayogo.bajp_subs1.data.repository.detail.DetailRepository
import com.aditprayogo.bajp_subs1.data.repository.detail.DetailRepositoryImpl
import com.aditprayogo.bajp_subs1.data.repository.movie.MovieRepository
import com.aditprayogo.bajp_subs1.data.repository.movie.MovieRepositoryImpl
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepository
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepositoryImpl
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

    @Provides
    @Singleton
    fun provideTvShowRepository(
        movieServices: MovieServices
    ) : TvShowRepository = TvShowRepositoryImpl(movieServices)

    @Provides
    @Singleton
    fun provideDetailRepository(
        movieServices: MovieServices
    ) : DetailRepository = DetailRepositoryImpl(movieServices)

}