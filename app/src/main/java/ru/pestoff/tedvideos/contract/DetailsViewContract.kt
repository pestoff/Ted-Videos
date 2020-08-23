package ru.pestoff.tedvideos.contract

import ru.pestoff.tedvideos.player.MediaPlayer

interface DetailsViewContract {

    interface Presenter {

        fun getPlayer(): MediaPlayer

        fun play(url: String)

        fun releasePlayer()
    }

    interface View
}
