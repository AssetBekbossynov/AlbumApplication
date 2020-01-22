package com.asset.data.service

import com.asset.data.entity.Album
import com.asset.data.entity.PhotoData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {

    @GET("albums")
    fun getAlbum(@Query("_start") start: Int,
                 @Query("_limit") limit: Int) : Observable<List<Album>>

    @GET("photos")
    fun getPhoto(@Query("albumId") albumId: Int) : Observable<List<PhotoData>>
}