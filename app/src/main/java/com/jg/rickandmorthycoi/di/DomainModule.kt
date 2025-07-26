package com.jg.rickandmorthycoi.di

import com.jg.rickandmorthycoi.domain.usecase.GetCharactersUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCharactersUseCase(get()) }
}