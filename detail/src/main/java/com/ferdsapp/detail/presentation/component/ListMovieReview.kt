package com.ferdsapp.detail.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ferdsapp.core.ui.theme.MovieAppTheme
import com.ferdsapp.core.ui.utils.FormatTimeHelper
import com.ferdsapp.detail.R
import com.ferdsapp.detail.data.model.movie_details.MovieDetailReviewResultResponse
import java.time.Instant

@Composable
fun ListMovieReview(
    movieReview: MovieDetailReviewResultResponse,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movieReview.author_details.avatar_path}",
            contentScale = ContentScale.Crop,
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.no_picture_profile),
            error = painterResource(id = R.drawable.no_picture_profile),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = movieReview.author_details.username ?: "Unknown Name",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = FormatTimeHelper.timeAgoFromIso(movieReview.updated_at ?: movieReview.created_at ?: Instant.now().toString()),
                    fontSize = 12.sp,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movieReview.content ?: "-",
                fontSize = 13.sp,
                textAlign = TextAlign.Justify,
//                color = Color.White.copy(alpha = 0.85f),
                lineHeight = 18.sp
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun ListMovieReviewPreview() {
    MovieAppTheme {
//        ListMovieReview()
    }
}