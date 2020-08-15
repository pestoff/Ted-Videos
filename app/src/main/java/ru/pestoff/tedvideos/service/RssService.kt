package ru.pestoff.tedvideos.service

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import ru.pestoff.tedvideos.model.RssFeed


interface RssService {

    @GET("themes/rss/id")
    fun getCats(): Call<RssFeed>

    @GET("themes/rss/id")
    fun rGetCats(): Observable<RssFeed>

    companion object {
        fun create(): RssService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.ted.com/")
                .build()

            return retrofit.create(RssService::class.java)
        }
    }
}
