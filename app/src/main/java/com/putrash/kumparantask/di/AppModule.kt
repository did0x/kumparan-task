package com.putrash.kumparantask.di

import com.putrash.data.Api
import com.putrash.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var appModule = module {
    factory { provideHttpLogging() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

fun provideHttpLogging() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient().newBuilder()
    .addInterceptor(loggingInterceptor)
    .build()

fun provideRetrofit(okHttp: OkHttpClient): Api = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttp)
    .baseUrl(BuildConfig.BASE_URL)
    .build()
    .create(Api::class.java)

