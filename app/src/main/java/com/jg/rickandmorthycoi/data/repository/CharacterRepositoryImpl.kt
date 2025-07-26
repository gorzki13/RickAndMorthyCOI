package com.jg.rickandmorthycoi.data.repository

import com.jg.rickandmorthycoi.data.remote.RickAndMortyApi
import com.jg.rickandmorthycoi.data.remote.toDomain
import com.jg.rickandmorthycoi.domain.model.Character

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<Character> {
        val response = api.getCharacters(page)
        return response.results.map { it.toDomain() }
    }
}