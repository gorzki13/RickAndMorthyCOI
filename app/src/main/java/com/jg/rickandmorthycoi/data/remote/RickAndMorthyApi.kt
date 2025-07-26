package com.jg.rickandmorthycoi.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

data class CharactersResponseDto(
    val results: List<CharacterDto>
)

data class CharacterDto(
    val id: Int,
    val name: String,
    val image: String
)

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): CharactersResponseDto
}