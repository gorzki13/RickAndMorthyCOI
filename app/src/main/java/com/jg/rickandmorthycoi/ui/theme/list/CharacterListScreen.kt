package com.jg.rickandmorthycoi.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jg.rickandmorthycoi.ui.components.CharacterListItem
import org.koin.androidx.compose.getViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.res.stringResource
import com.jg.rickandmorthycoi.R

@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = getViewModel(),
    onCharacterClick: (Int) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        is CharacterListUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is CharacterListUiState.Success -> {
            LazyColumn {
                items(uiState.characters) { char ->
                    CharacterListItem(character = char, onClick = { onCharacterClick(char.id) })
                }
            }
        }
        is CharacterListUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(stringResource(R.string.sth_went_wrong))
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = { viewModel.fetchCharacters() }) {
                        Text(stringResource(R.string.Retry))
                    }
                }
            }
        }
    }
}