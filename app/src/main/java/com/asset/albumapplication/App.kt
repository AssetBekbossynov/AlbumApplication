package com.asset.albumapplication

import androidx.multidex.MultiDexApplication
import com.asset.albumapplication.di.albumApp
import org.koin.android.ext.android.startKoin

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, albumApp)
    }

    companion object {
        @JvmStatic var instance: App? = null
            private set
    }
}