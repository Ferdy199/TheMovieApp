package com.ferdsapp.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ferdsapp.core.ui.theme.MovieAppTheme

@Composable
fun LoadingDialog(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            textAlign = TextAlign.Center,
            text = "Loading"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingDialogPreview() {
    MovieAppTheme {
        LoadingDialog()
    }
}

@Composable
fun EmptyDialog(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ){
        Text(
            textAlign = TextAlign.Center,
            text = "Empty Data"
        )
    }
}

@Composable
fun ErrorDialog(
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