package com.asset.data.repository.dataSource

import com.asset.data.entity.Album
import io.reactivex.Observable

interface AlbumDataStore {
    fun getAlbum(limit: Int, start: Int): Observable<List<Album>>
}