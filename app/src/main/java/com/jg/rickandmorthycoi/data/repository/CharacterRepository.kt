package com.jg.rickandmorthycoi.data.repository

import com.jg.rickandmorthycoi.domain.model.Character
import com.jg.rickandmorthycoi.domain.model.CharacterDetail

interface CharacterRepository {
    suspend fun getCharacters(page: Int = 1): List<Character>
    suspend fun getCharacterDetails(id: Int): CharacterDetail
}