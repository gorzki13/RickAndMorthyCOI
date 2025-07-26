package com.jg.rickandmorthycoi.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val FAVORITES_KEY = stringSetPreferencesKey("favorite_ids")

class FavoriteRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : FavoriteRepository {

    override fun getFavoriteIds(): Flow<Set<Int>> =
        dataStore.data.map { prefs ->
            prefs[FAVORITES_KEY]
                ?.mapNotNull { it.toIntOrNull() }
                ?.toSet()
                ?: emptySet()
        }

    override suspend fun toggleFavorite(id: Int) {
        dataStore.edit { prefs ->
            val current = prefs[FAVORITES_KEY]?.toMutableSet() ?: mutableSetOf()
            val key = id.toString()
            if (!current.add(key)) current.remove(key)
            prefs[FAVORITES_KEY] = current
        }
    }

    override suspend fun isFavorite(id: Int): Boolean =
        dataStore.data.map { prefs ->
            prefs[FAVORITES_KEY]?.contains(id.toString()) ?: false
        }.first()
}
