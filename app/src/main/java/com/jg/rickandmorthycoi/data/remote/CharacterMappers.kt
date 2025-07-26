package com.jg.rickandmorthycoi.data.remote

import com.jg.rickandmorthycoi.domain.model.Character
import com.jg.rickandmorthycoi.domain.model.CharacterDetail
import com.jg.rickandmorthycoi.util.yearsAgo

fun CharacterDto.toDomain(): Character =
    Character(
        id = this.id,
        name = this.name,
        imageUrl = this.image,
        isFavorite = false
    )


fun CharacterDetailDto.toDomain(): CharacterDetail =
    CharacterDetail(
        id = id,
        name = name,
        imageUrl = image,
        status = status,
        type = type.takeIf { it.isNotBlank() },
        gender = gender,
        originName = origin.name,
        createdYearsAgo = created.yearsAgo(),
        episodeUrls = episode
    )