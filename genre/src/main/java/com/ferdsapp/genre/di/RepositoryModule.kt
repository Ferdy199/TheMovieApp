package com.ferdsapp.genre.di

import com.ferdsapp.genre.data.repository.IMovieRepository
import com.ferdsapp.genre.data.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository) : IMovieRepository
}