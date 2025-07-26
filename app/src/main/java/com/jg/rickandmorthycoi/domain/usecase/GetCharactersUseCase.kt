package com.jg.rickandmorthycoi.domain.usecase

import com.jg.rickandmorthycoi.data.repository.CharacterRepository
import com.jg.rickandmorthycoi.domain.model.Character

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(page: Int = 1): List<Character> =
        repository.getCharacters(page)
}