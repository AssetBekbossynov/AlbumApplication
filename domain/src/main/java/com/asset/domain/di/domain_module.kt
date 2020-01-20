package com.asset.domain.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module

/**
 * Koin module, that create instances for domain layer dependencies
 */
const val EX_1 = "executorWork"
const val EX_2 = "executorMain"

val domainModule = module {



    single(EX_1) { Schedulers.io() }
    single(EX_2) { AndroidSchedulers.mainThread() }
}