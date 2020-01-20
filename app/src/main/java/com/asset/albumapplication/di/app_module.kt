package com.asset.albumapplication.di

import com.asset.data.di.dataModule
import com.asset.domain.di.domainModule
import org.koin.dsl.module.module


/**
 * Koin module, that create instances for presentation layer dependencies
 */
val appModule = module {

}

// Gather all app modules
val albumApp = listOf(appModule, dataModule, domainModule)
