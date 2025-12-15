package com.ferdsapp.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun LoadingDialog(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Text(
            textAlign = TextAlign.Center,
            text = "Loading"
        )
    }
}

@Composable
fun EmptyDialog(
    message: String? = "",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Text(
            textAlign = TextAlign.Center,
            text = "Empty $message"
        )
    }
}