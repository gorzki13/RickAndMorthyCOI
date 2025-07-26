package com.jg.rickandmorthycoi.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


data class OriginDto(
    val name: String
)

data class CharacterDetailDto(
    val id: Int,
    val name: String,
    val status: String,
    val type: String,
    val gender: String,
    val origin: OriginDto,
    val image: String,
    val episode: List<String>,
    val created: String
)

data class CharactersResponseDto(val results: List<CharacterDto>)

data class CharacterDto(val id: Int, val name: String, val image: String,  val episode: List<String> )

interface RickAndMortyApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int = 1): CharactersResponseDto

    @GET("api/character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDetailDto

}