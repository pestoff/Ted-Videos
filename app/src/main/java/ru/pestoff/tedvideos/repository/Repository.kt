package ru.pestoff.tedvideos.repository

import ru.pestoff.tedvideos.service.RssService

public class Repository(val service: RssService) {

    fun getRssFeed() = service.getCats().map {
        it.channel.items.toList()
    }
}
