package com.jg.rickandmorthycoi.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jg.rickandmorthycoi.data.repository.FavoriteRepository
import com.jg.rickandmorthycoi.data.repository.FavoriteRepositoryImpl
import com.jg.rickandmorthycoi.util.dataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStore<Preferences>> { androidContext().dataStore }
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
}
