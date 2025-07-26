package com.jg.rickandmorthycoi.data.repository

import kotlinx.coroutines.flow.Flow


interface FavoriteRepository {

    fun getFavoriteIds(): Flow<Set<Int>>


    suspend fun toggleFavorite(id: Int)


    suspend fun isFavorite(id: Int): Boolean
}
