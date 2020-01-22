package com.asset.data.entity

import com.asset.domain.entity.PhotoDomain

class PhotoDataMapper internal constructor(){
    fun transform(data: List<PhotoData>?): List<PhotoDomain>?{
        val domainList: MutableList<PhotoDomain> = arrayListOf()
        var domain: PhotoDomain
        if (data != null) {
            data.forEach {
                domain = PhotoDomain(it.albumId, it.id, it.title, it.url, it.thumbnailUrl)
                domainList.add(domain)
            }
        }
        return domainList
    }
}