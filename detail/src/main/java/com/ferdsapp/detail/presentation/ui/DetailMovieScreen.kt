package com.ferdsapp.detail.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.component.LoadingDialog
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import com.ferdsapp.detail.presentation.component.ListMovieGenre

@Composable
fun DetailMovieScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    movieId: Int,
    modifier: Modifier = Modifier
) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Empty -> EmptyDialog()
            is UiState.Error -> {
                Log.d("DetailMovieScreen", "DetailMovieScreen: ${uiState.errorMessage}")
                EmptyDialog()
            }
            is UiState.Loading -> {
                viewModel.getDetailMovie(movieId)
                LoadingDialog()
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailMovieScreenContent(data)
            }
        }
    }

}

@Composable
fun DetailMovieScreenContent(
    movieDetail: MovieDetailsResponse,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.fillMaxSize()
    ){
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movieDetail.backdrop_path}",
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp),

            ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movieDetail.poster_path}",
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .heightIn(min = 180.dp, max = 240.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = movieDetail.original_title ?: "",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily.SansSerif,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(80.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.heightIn(max = 160.dp)
                ) {
                    items(movieDetail.genres.orEmpty(), key = {it.id}){ genreList ->
                        ListMovieGenre(genreList.name ?: "")
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp, bottom = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Overview",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movieDetail.overview ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.W200,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Justify,
            )
        }
    }
}