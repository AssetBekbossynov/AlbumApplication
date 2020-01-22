package com.asset.data.repository.dataSource

import com.asset.data.entity.Album
import com.asset.data.entity.PhotoData
import io.reactivex.Observable

interface AlbumDataStore {
    fun getAlbum(limit: Int, start: Int): Observable<List<Album>>
    fun getPhoto(albumId: Int): Observable<List<PhotoData>>
}