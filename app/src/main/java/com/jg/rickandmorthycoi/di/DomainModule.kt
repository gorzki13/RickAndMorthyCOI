package com.jg.rickandmorthycoi.di

import com.jg.rickandmorthycoi.domain.usecase.GetCharacterDetailsUseCase
import com.jg.rickandmorthycoi.domain.usecase.GetCharactersUseCase
import com.jg.rickandmorthycoi.domain.usecase.GetFavoriteIdsUseCase
import com.jg.rickandmorthycoi.domain.usecase.ToggleFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCharactersUseCase(get()) }
    factory { GetCharacterDetailsUseCase(get()) }
    factory { ToggleFavoriteUseCase(get()) }
    factory { GetFavoriteIdsUseCase(get()) }
}