package com.jg.rickandmorthycoi.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jg.rickandmorthycoi.R
import com.jg.rickandmorthycoi.ui.detail.CharacterDetailUiState

@Composable
fun CharacterDetailCard(
    detail: CharacterDetailUiState.Success,
    onToggleFavorite: () -> Unit
) {
    val data = detail.detail
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
            AsyncImage(
                model = data.imageUrl,
                contentDescription = data.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = data.name,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "${data.status} • ${data.gender}",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            data.type?.let {
                Spacer(Modifier.height(6.dp))
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            Spacer(Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.origins, data.originName),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.created___years_ago, data.createdYearsAgo),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.episodes),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            data.episodeUrls.take(5).forEach { url ->
                Text(
                    text = "• ${url.substringAfterLast("/")}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 32.dp, end = 24.dp, top = 4.dp)
                )
            }

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = if (detail.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (detail.isFavorite)
                        MaterialTheme.colorScheme.secondary
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { onToggleFavorite() }
                )
            }
        }
    }
}
