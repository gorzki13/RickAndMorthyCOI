package com.jg.rickandmorthycoi.domain.usecase

import com.jg.rickandmorthycoi.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow


class GetFavoriteIdsUseCase(
    private val repository: FavoriteRepository
) {
    operator fun invoke(): Flow<Set<Int>> =
        repository.getFavoriteIds()
}
