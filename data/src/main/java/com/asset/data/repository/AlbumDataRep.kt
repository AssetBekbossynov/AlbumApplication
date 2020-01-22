package com.asset.data.repository

import com.asset.data.entity.AlbumMapper
import com.asset.data.entity.PhotoDataMapper
import com.asset.data.repository.dataSource.CloudAlbumDataStore
import com.asset.domain.entity.AlbumDomain
import com.asset.domain.entity.PhotoDomain
import com.asset.domain.repository.AlbumRep
import io.reactivex.Observable

class AlbumDataRep(private val storage: CloudAlbumDataStore): AlbumRep{
    override fun getAlbum(limit: Int, start: Int): Observable<List<AlbumDomain>> {
        return storage.getAlbum(limit, start).map { AlbumMapper().transform(it) }
    }

    override fun getPhoto(albumId: Int): Observable<List<PhotoDomain>> {
        return storage.getPhoto(albumId).map { PhotoDataMapper().transform(it) }
    }
}