package com.aditprayogo.bajp_subs1.di

import com.aditprayogo.bajp_subs1.data.repository.MovieRepository
import com.aditprayogo.bajp_subs1.domain.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

/**
 * Created by Aditiya Prayogo.
 */
@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideMovieUseCase(
        movieRepository : MovieRepository
    ) : MovieUseCase = MovieUseCase(movieRepository)

}