package com.ferdsapp.movieapp.presentation.screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieTopBar(
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    onMenuClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        },
        title = {
            Text(
                text = "Movie App"
            )
        }
    )
}