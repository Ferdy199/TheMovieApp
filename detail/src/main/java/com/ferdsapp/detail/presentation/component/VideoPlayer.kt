package com.ferdsapp.detail.presentation.component

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    context: Context,
    url: String,
    modifier: Modifier = Modifier,
    autoplay: Boolean = true
) {

    val player = remember {
        ExoPlayer.Builder(context).build()
    }


    LaunchedEffect("https://www.youtube.com/watch?v=7HgAN5cEmkk") {
        player.setMediaItem(MediaItem.fromUri("https://www.youtube.com/watch?v=7HgAN5cEmkk"))
        player.prepare()
        player.playWhenReady = autoplay
    }


    DisposableEffect(Unit) {
        onDispose { player.release() }
    }

    AndroidView(
        modifier = modifier,
        factory = {
            PlayerView(it).apply {
                this.player = player
                useController = true
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { it.player = player }
    )
}