package ru.pestoff.tedvideos.presenter

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.pestoff.tedvideos.contract.ListVideoContract
import ru.pestoff.tedvideos.repository.Repository
import ru.pestoff.tedvideos.service.RssService
import java.lang.ref.WeakReference

class ListVideoPresenter(listView: ListVideoContract.View) : ListVideoContract.Presenter {

    private val view = WeakReference(listView)

    val repository = Repository(RssService.create())

    @SuppressLint("CheckResult")
    override fun loadDataFromRss() {
        repository.getRssFeed()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe() {
                view.get()?.getAdapter()?.videos = it
            }
    }
}