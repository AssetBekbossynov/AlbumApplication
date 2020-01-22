package com.asset.domain.di

import com.asset.domain.entity.AlbumDomain
import com.asset.domain.entity.PhotoDomain
import com.asset.domain.interactor.GetAlbum
import com.asset.domain.interactor.GetPhoto
import com.asset.domain.interactor.UseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

/**
 * Koin module, that create instances for domain layer dependencies
 */
const val EX_1 = "executorWork"
const val EX_2 = "executorMain"

const val GET_ALBUM = "GET_ALBUM"
const val GET_PHOTO = "GET_PHOTO"

val domainModule = module {

    factory(GET_ALBUM) {GetAlbum(get(), get(EX_1), get(EX_2)) as UseCase<List<AlbumDomain>, GetAlbum.Params>}
    factory(GET_PHOTO) {GetPhoto(get(), get(EX_1), get(EX_2)) as UseCase<List<PhotoDomain>, GetPhoto.Params>}

    single(EX_1) { Schedulers.io() }
    single(EX_2) { AndroidSchedulers.mainThread() }
}