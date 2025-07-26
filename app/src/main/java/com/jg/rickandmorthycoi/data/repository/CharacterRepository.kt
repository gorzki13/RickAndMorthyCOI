package com.jg.rickandmorthycoi.data.repository

import com.jg.rickandmorthycoi.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(page: Int = 1): List<Character>
}