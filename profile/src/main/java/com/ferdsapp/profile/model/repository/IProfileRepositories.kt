package com.ferdsapp.profile.model.repository

import com.ferdsapp.profile.model.data.ProfileDataEntities
import kotlinx.coroutines.flow.Flow

interface IProfileRepositories {
    fun getProfileData(): Flow<ProfileDataEntities>
}