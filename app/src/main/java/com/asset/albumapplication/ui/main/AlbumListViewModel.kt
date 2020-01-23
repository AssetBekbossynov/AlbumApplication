package com.asset.albumapplication.ui.main

import androidx.lifecycle.MutableLiveData
import com.asset.albumapplication.util.Resource
import com.asset.domain.entity.AlbumDomain
import com.asset.domain.interactor.GetAlbum
import com.asset.domain.interactor.UseCase

class AlbumListViewModel(val getAlbum: UseCase<List<AlbumDomain>, GetAlbum.Params>){

    var albumList = MutableLiveData<Resource<List<AlbumDomain>>>()

    fun getAlbum(limit: Int = 10, start: Int) {
        getAlbum.execute(GetAlbum.Params.getAlbum(limit, start), {
            albumList.value = Resource.success(it)
        },{
            albumList.value = Resource.error(it.message!!)
        }, onSubscribe = {
            albumList.value = Resource.loading()
        })
    }
}