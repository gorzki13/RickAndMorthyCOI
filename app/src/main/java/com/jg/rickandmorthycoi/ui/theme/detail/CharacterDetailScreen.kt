package com.jg.rickandmorthycoi.ui.theme.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jg.rickandmorthycoi.ui.detail.CharacterDetailUiState
import com.jg.rickandmorthycoi.ui.detail.CharacterDetailViewModel
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
                title = { Text("Szczegóły", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Powrót")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (uiState) {
                is CharacterDetailUiState.Loading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is CharacterDetailUiState.Success -> {
                    val data = uiState as CharacterDetailUiState.Success

                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        shape = RoundedCornerShape(24.dp),
                        elevation = CardDefaults.cardElevation(12.dp)
                    ) {
                        Column(Modifier.fillMaxSize()) {
                            // Obrazek
                            AsyncImage(
                                model = data.detail.imageUrl,
                                contentDescription = data.detail.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                            )

                            Spacer(Modifier.height(16.dp))

                            // Duży nagłówek
                            Text(
                                text = data.detail.name,
                                style = MaterialTheme.typography.displayLarge,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )

                            Spacer(Modifier.height(8.dp))

                            // Podtytuł
                            Text(
                                text = "${data.detail.status} • ${data.detail.gender}",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )

                            data.detail.type?.let {
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(horizontal = 24.dp)
                                )
                            }

                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = "Pochodzenie: ${data.detail.originName}",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = "Utworzony ${data.detail.createdYearsAgo} lat temu",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )

                            Spacer(Modifier.height(16.dp))

                            Text(
                                text = "Odcinki",
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                            data.detail.episodeUrls.take(5).forEach { url ->
                                Text(
                                    text = "• ${url.substringAfterLast("/")}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 32.dp, end = 24.dp, top = 4.dp)
                                )
                            }

                            Spacer(Modifier.weight(1f))

                            // Serce na dole
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    imageVector = if (data.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    tint = if (data.isFavorite)
                                        MaterialTheme.colorScheme.secondary
                                    else
                                        MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clickable { viewModel.onToggleFavorite() }
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
                        Text(
                            "Ups: ${(uiState as CharacterDetailUiState.Error).message}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(Modifier.height(12.dp))
                        Button(onClick = { viewModel.init(characterId) }) {
                            Text("Ponów", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}