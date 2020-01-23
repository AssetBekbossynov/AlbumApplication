package com.asset.albumapplication.ui.album

import androidx.lifecycle.MutableLiveData
import com.asset.albumapplication.util.Resource
import com.asset.domain.entity.PhotoDomain
import com.asset.domain.interactor.GetPhoto
import com.asset.domain.interactor.UseCase

class PhotoListViewModel(val getPhoto: UseCase<List<PhotoDomain>, GetPhoto.Params>){

    var photoList = MutableLiveData<Resource<List<PhotoDomain>>>()

    fun getPhoto(albumId: Int) {
        getPhoto.execute(GetPhoto.Params.getPhoto(albumId), {
            photoList.value = Resource.success(it)
        },{
            photoList.value = Resource.error(it.message!!)
        }, onSubscribe = {
            photoList.value = Resource.loading()
        })
    }
}