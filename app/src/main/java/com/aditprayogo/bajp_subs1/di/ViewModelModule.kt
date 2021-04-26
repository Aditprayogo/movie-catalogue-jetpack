package com.aditprayogo.bajp_subs1.di

import com.aditprayogo.bajp_subs1.data.repository.detail.DetailRepository
import com.aditprayogo.bajp_subs1.data.repository.movie.MovieRepository
import com.aditprayogo.bajp_subs1.data.repository.tv_show.TvShowRepository
import com.aditprayogo.bajp_subs1.domain.detail.DetailUseCase
import com.aditprayogo.bajp_subs1.domain.movie.MovieUseCase
import com.aditprayogo.bajp_subs1.domain.tv_show.TvShowUseCase
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

    @Provides
    @ViewModelScoped
    fun provideTvShowUseCase(
        tvShowRepository: TvShowRepository
    ) : TvShowUseCase = TvShowUseCase(tvShowRepository)

    @Provides
    @ViewModelScoped
    fun provideDetailUseCase(
        detailRepository: DetailRepository
    ) : DetailUseCase = DetailUseCase(detailRepository)

}