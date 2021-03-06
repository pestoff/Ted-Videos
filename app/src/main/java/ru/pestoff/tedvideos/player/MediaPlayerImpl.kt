package ru.pestoff.tedvideos.player

import android.content.Context
import android.net.Uri
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import ru.pestoff.tedvideos.R

class MediaPlayerImpl : MediaPlayer {

    private lateinit var exoPlayer: ExoPlayer
    private lateinit var context: Context

    private fun initializePlayer() {
        val trackSelector = DefaultTrackSelector()
        val loadControl = DefaultLoadControl()
        val renderFactory = DefaultRenderersFactory(context)

        exoPlayer = ExoPlayerFactory.newSimpleInstance(context,
            renderFactory, trackSelector, loadControl
        )
    }

    override fun play(url: String) {
        val userAgent = Util.getUserAgent(context, context.getString(R.string.app_name))

        val mediaSource = ExtractorMediaSource
            .Factory(DefaultDataSourceFactory(context, userAgent))
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(Uri.parse(url))

        exoPlayer.prepare(mediaSource)

        exoPlayer.playWhenReady = true
    }

    override fun getPlayerImpl(context: Context): ExoPlayer {
        this.context = context
        initializePlayer()
        return exoPlayer
    }

    override fun releasePlayer() {
        exoPlayer.stop()
        exoPlayer.release()
    }
}
