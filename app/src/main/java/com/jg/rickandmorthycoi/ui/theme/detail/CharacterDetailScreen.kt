package com.jg.rickandmorthycoi.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBack: () -> Unit,
    viewModel: CharacterDetailViewModel = getViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Szczegóły") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Powrót")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {
                is CharacterDetailUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is CharacterDetailUiState.Success -> {
                    val data = uiState as CharacterDetailUiState.Success
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = data.detail.imageUrl,
                            contentDescription = data.detail.name,
                            modifier = Modifier.size(200.dp)
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(data.detail.name, style = MaterialTheme.typography.headlineSmall)
                        Text(
                            "${data.detail.status} — ${data.detail.gender}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        data.detail.type?.let {
                            Text(it, style = MaterialTheme.typography.bodySmall)
                        }
                        Text(
                            "Pochodzenie: ${data.detail.originName}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            "Utworzony ${data.detail.createdYearsAgo} lat temu",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(Modifier.height(8.dp))
                        Text("Odcinki:", style = MaterialTheme.typography.titleMedium)
                        data.detail.episodeUrls
                            .take(5)
                            .forEach { url ->
                                Text(
                                    "• ${url.substringAfterLast("/")}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        Spacer(Modifier.height(16.dp))
                        IconButton(onClick = { viewModel.onToggleFavorite() }) {
                            if (data.isFavorite) {
                                Icon(Icons.Filled.Favorite, contentDescription = "Usuń z ulubionych")
                            } else {
                                Icon(
                                    Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Dodaj do ulubionych"
                                )
                            }
                        }
                    }
                }
                is CharacterDetailUiState.Error -> {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Ups: ${(uiState as CharacterDetailUiState.Error).message}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.init(characterId) }) {
                            Text("Ponów")
                        }
                    }
                }
            }
        }
    }
}
