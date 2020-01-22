package com.asset.albumapplication.di

import com.asset.albumapplication.ui.album.PhotoListViewModel
import com.asset.albumapplication.ui.main.AlbumListViewModel
import com.asset.data.di.dataModule
import com.asset.domain.di.GET_ALBUM
import com.asset.domain.di.GET_PHOTO
import com.asset.domain.di.domainModule
import org.koin.dsl.module.module


/**
 * Koin module, that create instances for presentation layer dependencies
 */
val appModule = module {
    factory { AlbumListViewModel(get(GET_ALBUM))}
    factory { PhotoListViewModel(get(GET_PHOTO))}
}

// Gather all app modules
val albumApp = listOf(appModule, dataModule, domainModule)
