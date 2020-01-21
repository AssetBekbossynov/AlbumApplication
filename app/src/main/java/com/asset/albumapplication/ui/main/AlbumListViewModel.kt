package com.asset.albumapplication.ui.main

import com.asset.domain.entity.AlbumDomain
import com.asset.domain.interactor.GetAlbum
import com.asset.domain.interactor.UseCase
import io.reactivex.Observable
import io.reactivex.internal.operators.observable.ObservableFromArray

class AlbumListViewModel(val getAlbum: UseCase<List<AlbumDomain>, GetAlbum.Params>){

    val albumList: Observable<List<AlbumDomain>>

    fun getAlbum() {
        getAlbum.execute({
            it.forEach {
                albumList.add(it)
            }
        },{

        })
    }

}