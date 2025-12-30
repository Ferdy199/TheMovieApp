package com.ferdsapp.profile.Presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.component.ErrorDialog
import com.ferdsapp.core.ui.component.LoadingDialog
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.core.ui.theme.MovieAppTheme
import com.ferdsapp.profile.Presentation.viewModel.ProfileViewModel
import com.ferdsapp.profile.R
import com.ferdsapp.profile.model.data.ProfileDataEntities

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getProfileData()
    }
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Empty -> EmptyDialog()
            is UiState.Error -> ErrorDialog()
            is UiState.Loading -> {
                LoadingDialog()
            }
            is UiState.Success -> {
                val data = uiState.data
                ProfileScreenContent(
                    profileData = data,
                    modifier = modifier
                )
            }
        }
    }

}


@Composable
fun ProfileScreenContent(
    profileData: ProfileDataEntities,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(profileData.profilePicture),
                contentDescription = "profile_picture",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(180.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier.height(16.dp))
            Text(
                text = profileData.name,
                textAlign = TextAlign.Center
            )
            Spacer(modifier.height(4.dp))
            Text(
                text = profileData.email,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    MovieAppTheme {
        ProfileScreenContent(
            ProfileDataEntities(
                name = "Ferdy Rahmaesa Suarial",
                email = "sfddsf@gmail.com",
                profilePicture = R.drawable.profile_picture
            ),
            modifier = Modifier)
    }
}