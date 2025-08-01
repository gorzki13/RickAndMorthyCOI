package com.jg.rickandmorthycoi.domain.model

data class Character(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean = false,
    val episodesCount: Int = 0
)