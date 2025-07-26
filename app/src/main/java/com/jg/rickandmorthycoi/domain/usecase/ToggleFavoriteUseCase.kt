package com.jg.rickandmorthycoi.domain.usecase

import com.jg.rickandmorthycoi.data.repository.FavoriteRepository

class ToggleFavoriteUseCase(
     val repository: FavoriteRepository
) {

    suspend operator fun invoke(id: Int) {
        repository.toggleFavorite(id)
    }
}