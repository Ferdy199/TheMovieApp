package com.ferdsapp.search.di

import com.ferdsapp.search.data.repository.ISearchMovieRepository
import com.ferdsapp.search.data.repository.SearchMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [SearchNetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(homeRepository: SearchMovieRepository): ISearchMovieRepository
}