package com.jg.rickandmorthycoi.domain.model

data class CharacterDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String,
    val type: String?,
    val gender: String,
    val originName: String,
    val createdYearsAgo: Int,
    val episodeUrls: List<String>
)