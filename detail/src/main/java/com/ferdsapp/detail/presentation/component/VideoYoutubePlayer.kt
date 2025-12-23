package com.ferdsapp.detail.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun VideoYoutubePlayer(
    videoId: String,
    autoplay: Boolean = true,
    modifier: Modifier = Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var playerView: YouTubePlayerView? by remember { mutableStateOf(null) }

    DisposableEffect(Unit) {
        onDispose {
            playerView?.release()
            playerView = null
        }
    }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            YouTubePlayerView(context).also { view ->
                playerView = view
                view.enableAutomaticInitialization = false
                lifecycleOwner.lifecycle.addObserver(view)

                view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                       if (autoplay){
                           youTubePlayer.loadVideo(videoId, 0f)
                       }else{
                           youTubePlayer.cueVideo(videoId, 0f)
                       }
                    }
                })
            }
        }
    )
}