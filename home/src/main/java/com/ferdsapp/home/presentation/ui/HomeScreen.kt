import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.component.ErrorDialog
import com.ferdsapp.core.ui.component.LoadingDialog
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.presentation.component.MovieListItem
import com.ferdsapp.home.presentation.ui.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val items = homeViewModel.getNowPlayingResponse.collectAsLazyPagingItems()

    when (val refresh = items.loadState.refresh) {
        is LoadState.Loading -> LoadingDialog()
        is LoadState.Error -> ErrorDialog(refresh.error.message ?: "Error Data")
        else -> {
            if (items.itemCount == 0) EmptyDialog()
            else HomeScreenContent(
                nowPlayingData = items,
                navigateToDetail = navigateToDetail,
                modifier = modifier
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    nowPlayingData: LazyPagingItems<ResultNowPlayingResponses>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = "Movie Now Playing",
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(
                count = nowPlayingData.itemCount,
                key = { index -> nowPlayingData[index]?.id ?: "placeholder-$index" } // ✅ paling aman
            ) { index ->
                val movieData = nowPlayingData[index]
                if (movieData != null) {
                    MovieListItem(
                        backdrop_path = movieData.backdrop_path,
                        title = movieData.title,
                        modifier = Modifier.clickable { navigateToDetail(movieData.id) }
                    )
                } else {
                    Spacer(Modifier.height(120.dp)) // ✅ placeholder stabil
                }
            }
        }
    }
}
