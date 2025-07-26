package com.jg.rickandmorthycoi.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_NAME = "favorites_prefs"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DATASTORE_NAME
)