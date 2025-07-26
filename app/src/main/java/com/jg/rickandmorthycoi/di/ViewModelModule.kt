package com.jg.rickandmorthycoi.di

import com.jg.rickandmorthycoi.ui.list.CharacterListViewModel
import com.jg.rickandmorthycoi.ui.detail.CharacterDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CharacterListViewModel(
            getCharacters = get(),
            getFavoriteIds = get()
        )
    }


    viewModel { (characterId: Int) ->
        CharacterDetailViewModel(
            getDetails       = get(),
            getFavoriteIds   = get(),
            toggleFavorite   = get()
        ).apply {
            init(characterId)
        }
    }
}
