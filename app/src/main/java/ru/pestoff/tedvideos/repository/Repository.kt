package ru.pestoff.tedvideos.repository


import android.util.Log
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.pestoff.tedvideos.model.Item
import ru.pestoff.tedvideos.model.RssFeed
import ru.pestoff.tedvideos.service.RssService

public class Repository(val service: RssService) {

    fun getRssFeed() = service.rGetCats().map {
        it.channel.items.toList()
    }
}