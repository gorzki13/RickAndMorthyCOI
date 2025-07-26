package com.jg.rickandmorthycoi.domain.usecase

import com.jg.rickandmorthycoi.data.repository.CharacterRepository
import com.jg.rickandmorthycoi.domain.model.CharacterDetail

class GetCharacterDetailsUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): CharacterDetail =
        repository.getCharacterDetails(id)
}