package com.jg.rickandmorthycoi.ui.theme.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBack: () -> Unit
) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = onBack) {
            Text("Back from detail($characterId)")
        }
    }
}
