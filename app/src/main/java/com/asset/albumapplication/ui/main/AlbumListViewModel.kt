package com.asset.albumapplication.ui.main

import androidx.lifecycle.MutableLiveData
import com.asset.domain.entity.AlbumDomain
import com.asset.domain.interactor.GetAlbum
import com.asset.domain.interactor.UseCase

class AlbumListViewModel(val getAlbum: UseCase<List<AlbumDomain>, GetAlbum.Params>){

    var albumList = MutableLiveData<AlbumDomain>()

    fun getAlbum(limit: Int, start: Int) {
        getAlbum.execute(GetAlbum.Params.getAlbum(limit, start), {
            it.forEach {
                albumList.value = it
            }
        },{

        })
    }
}