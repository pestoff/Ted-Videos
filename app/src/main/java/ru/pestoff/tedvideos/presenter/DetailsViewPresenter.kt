package ru.pestoff.tedvideos.presenter

import ru.pestoff.tedvideos.contract.DetailsViewContract
import ru.pestoff.tedvideos.player.MediaPlayerImpl
import java.lang.ref.WeakReference

class DetailsViewPresenter(detailsView: DetailsViewContract.View) : DetailsViewContract.Presenter {

    private val view = WeakReference(detailsView)

    private val mediaPlayer = MediaPlayerImpl()

    override fun getPlayer() = mediaPlayer

    override fun play(url: String) = mediaPlayer.play(url)

    override fun releasePlayer() = mediaPlayer.releasePlayer()
}