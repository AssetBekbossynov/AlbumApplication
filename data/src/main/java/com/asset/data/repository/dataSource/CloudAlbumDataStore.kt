package com.asset.data.repository.dataSource

import com.asset.data.entity.Album
import com.asset.data.service.AlbumService
import io.reactivex.Observable

class CloudAlbumDataStore (private val service: AlbumService) : AlbumDataStore {

    override fun getAlbum(limit: Int, start: Int): Observable<List<Album>> {
        return service.getAlbum(start, limit)
    }
}