package com.ferdsapp.profile.model.source

import com.ferdsapp.profile.R
import com.ferdsapp.profile.model.data.ProfileDataEntities
import javax.inject.Inject

class ProfileDataSource @Inject constructor(){
    fun profileSource(): ProfileDataEntities {
        return ProfileDataEntities(
            name = "Ferdy Rahmaesa Suarial",
            email = "ferdyrahmaesa@gmail.com",
            profilePicture = R.drawable.profile_picture
        )
    }
}