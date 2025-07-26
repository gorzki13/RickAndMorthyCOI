package com.jg.rickandmorthycoi.data.remote

import com.jg.rickandmorthycoi.domain.model.Character

fun CharacterDto.toDomain(): Character =
    Character(
        id = this.id,
        name = this.name,
        imageUrl = this.image
    )