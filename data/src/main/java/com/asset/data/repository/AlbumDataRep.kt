package com.asset.data.repository

import com.asset.data.entity.AlbumMapper
import com.asset.data.repository.dataSource.CloudAlbumDataStore
import com.asset.domain.entity.AlbumDomain
import com.asset.domain.repository.AlbumRep
import io.reactivex.Observable

class AlbumDataRep(private val storage: CloudAlbumDataStore): AlbumRep{
    override fun getAlbum(limit: Int, start: Int): Observable<List<AlbumDomain>> {
        return storage.getAlbum(limit, start).map { AlbumMapper().transform(it) }
    }
}