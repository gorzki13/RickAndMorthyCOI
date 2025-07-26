package com.jg.rickandmorthycoi.di

import com.jg.rickandmorthycoi.ui.list.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharacterListViewModel(get()) }
}