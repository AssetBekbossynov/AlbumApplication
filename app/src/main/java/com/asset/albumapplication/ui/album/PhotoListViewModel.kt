package com.asset.albumapplication.ui.album

import androidx.lifecycle.MutableLiveData
import com.asset.domain.entity.PhotoDomain
import com.asset.domain.interactor.GetPhoto
import com.asset.domain.interactor.UseCase

class PhotoListViewModel(val getPhoto: UseCase<List<PhotoDomain>, GetPhoto.Params>){

    var photoList = MutableLiveData<PhotoDomain>()

    fun getPhoto(albumId: Int) {
        getPhoto.execute(GetPhoto.Params.getPhoto(albumId), {
            it.forEach {
                photoList.value = it
            }
        },{

        })
    }
}