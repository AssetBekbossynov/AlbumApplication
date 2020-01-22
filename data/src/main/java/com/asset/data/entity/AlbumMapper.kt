package com.asset.data.entity

import com.asset.domain.entity.AlbumDomain

class AlbumMapper internal constructor(){

    fun transform(data: List<Album>?): List<AlbumDomain>?{
        val domainList: MutableList<AlbumDomain> = arrayListOf()
        var domain: AlbumDomain
        if (data != null) {
            data.forEach {
                domain = AlbumDomain(it.id, it.title)
                domainList.add(domain)
            }
        }
        return domainList
    }
}