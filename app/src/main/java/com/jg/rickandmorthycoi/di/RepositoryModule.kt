package com.jg.rickandmorthycoi.di

import com.jg.rickandmorthycoi.data.repository.CharacterRepository
import com.jg.rickandmorthycoi.data.repository.CharacterRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<CharacterRepository> {
        CharacterRepositoryImpl(get())
    }
}