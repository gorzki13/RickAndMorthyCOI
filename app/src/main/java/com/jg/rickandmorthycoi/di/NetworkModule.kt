package com.jg.rickandmorthycoi.di

import com.jg.rickandmorthycoi.data.remote.RickAndMortyApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single<RickAndMortyApi> {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }
}