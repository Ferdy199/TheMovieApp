package com.ferdsapp.profile.model.repository

import com.ferdsapp.profile.model.data.ProfileDataEntities
import com.ferdsapp.profile.model.source.ProfileDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : IProfileRepositories {
    override fun getProfileData(): Flow<ProfileDataEntities> {
        return flow {
            try {
                emit(profileDataSource.profileSource())
            }catch (e: Exception){
                throw e
            }
        }
    }
}