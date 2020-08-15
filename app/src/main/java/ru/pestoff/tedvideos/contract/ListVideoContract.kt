package ru.pestoff.tedvideos.contract

import ru.pestoff.tedvideos.adapter.VideoAdapter

interface ListVideoContract {

    interface Presenter {
        fun loadDataFromRss()
    }

    interface View {
        fun getAdapter() : VideoAdapter
    }
}