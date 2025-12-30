package com.ferdsapp.detail.presentation.ui

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.component.ErrorDialog
import com.ferdsapp.core.ui.component.LoadingDialog
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiState
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.core.ui.utils.FormatTimeHelper
import com.ferdsapp.detail.R
import com.ferdsapp.detail.data.model.movie_details.MovieDetailReviewResultResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import com.ferdsapp.detail.presentation.component.ListMovieGenre
import com.ferdsapp.detail.presentation.component.ListMovieReview
import com.ferdsapp.detail.presentation.component.VideoYoutubePlayer

@Composable
fun DetailMovieScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    movieId: Int,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        viewModel.getDetailMovie(movieId)
        viewModel.getMovieReviewResponse(movieId)
    }

    val reviewItems = viewModel.uiMovieReviewState.collectAsLazyPagingItems()
    val detailState = viewModel.uiState.collectAsState(initial = UiState.Loading).value
    val reviewState = reviewItems.asUiState()
    Log.d("Detail Review", reviewState.toString())

    when (detailState) {
        is UiState.Empty -> EmptyDialog()
        is UiState.Error -> ErrorDialog("Error Detail ${detailState.errorMessage}")
        is UiState.Loading -> LoadingDialog()
        is UiState.Success -> {
            when (reviewState) {
                is UiState.Error -> {
                    Log.d("Detail Review", reviewState.errorMessage)
                    DetailMovieScreenContent(
                        movieDetail = detailState.data,
                        movieReview = null,
                        modifier = modifier
                    )
//                    ErrorDialog("Error Review ${reviewState.errorMessage}")
                }

                is UiState.Loading -> {
                    LoadingDialog()
                }

                is UiState.Empty -> {
                    DetailMovieScreenContent(
                        movieDetail = detailState.data,
                        movieReview = null,
                        modifier = modifier
                    )
                }

                is UiState.Success -> {
                    DetailMovieScreenContent(
                        movieDetail = detailState.data,
                        movieReview = reviewItems,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
fun DetailMovieScreenContent(
    movieDetail: MovieDetailsResponse? = null,
    movieReview: LazyPagingItems<MovieDetailReviewResultResponse>? = null,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {

        item {
            if (!movieDetail?.movieTrailer?.results.isNullOrEmpty()){
                val teaserKey = movieDetail.movieTrailer.results
                    .firstOrNull { it.type.equals("Teaser", ignoreCase = true) && it.site == "YouTube" }
                    ?.key

                Log.d("Detail Movie", "DetailMovieScreenContent: $teaserKey")

                VideoYoutubePlayer(
                    videoId = teaserKey ?: "",
                    autoplay = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                )
            }else{
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movieDetail?.backdrop_path}",
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp)
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${movieDetail?.poster_path}",
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.noimage),
                    error = painterResource(R.drawable.noimage),
                    modifier = Modifier
                        .heightIn(min = 180.dp, max = 240.dp)
                        .aspectRatio(2f / 3f)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = movieDetail?.original_title ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600,
                        fontFamily = FontFamily.SansSerif,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFF9800) // orange
                        )
                        Spacer(Modifier.width(6.dp))

                        Text(
                            text = String.format("%.1f", movieDetail?.vote_average),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.width(6.dp))

                        Text(
                            text = "/10",
                            color = Color.Black.copy(alpha = 0.6f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(Modifier.width(16.dp))

                        Text(
                            text = FormatTimeHelper.formatDuration(movieDetail!!.runtime),
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = modifier
                                .background(
                                    color = Color(0xFF2B2B2B), // pill gelap
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(80.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.heightIn(max = 160.dp)
                    ) {
                        items(movieDetail?.genres.orEmpty(), key = { it.id }) { genreList ->
                            ListMovieGenre(genreList.name ?: "")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Column(modifier = Modifier.padding(start = 16.dp, end = 8.dp)) {
                Text(
                    text = "Overview",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movieDetail?.overview ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W200,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Justify
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Review",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (movieReview != null) {
            items(
                count = movieReview.itemCount,
                key = { index -> movieReview[index]?.id ?: index }
            ) { index ->
                val movieReview = movieReview[index] ?: return@items
                ListMovieReview(movieReview)
            }
        } else {
            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Belum ada review.",
                    fontSize = 14.sp
                )
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) } // padding bawah
    }
}
