package com.asset.data.di

import com.asset.data.BuildConfig
import com.asset.data.exception.RxErrorHandlingCallAdapterFactory
import com.asset.data.repository.AlbumDataRep
import com.asset.data.repository.dataSource.CloudAlbumDataStore
import com.asset.data.service.AlbumService
import com.asset.data.util.Logger
import com.asset.domain.repository.AlbumRep
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Koin module, that create instances for data layer dependencies
 */
val dataModule = module {

    single { createOkHttpClient() }

    single { AlbumDataRep(get()) as AlbumRep }
    single { createService<AlbumService>(get(), BuildConfig.API_ADDRESS) }
    factory { CloudAlbumDataStore(get()) }

}


/**
 * Creates singleton okHttp client
 */
fun createOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.api(message) })
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()
}

/**
 * Creates AuthService from okHttp client
 */
inline fun <reified T> createService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}
