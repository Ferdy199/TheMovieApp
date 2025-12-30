package com.ferdsapp.profile.di

import com.ferdsapp.profile.model.repository.IProfileRepositories
import com.ferdsapp.profile.model.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileModule {

    @Binds
    @Singleton
    abstract fun bindProfileRepository(
        repository: ProfileRepository
    ): IProfileRepositories
}