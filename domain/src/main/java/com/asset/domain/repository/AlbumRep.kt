package com.asset.domain.repository

import com.asset.domain.entity.AlbumDomain
import io.reactivex.Observable

interface AlbumRep {
    fun getAlbum(limit: Int, start: Int): Observable<List<AlbumDomain>>

}