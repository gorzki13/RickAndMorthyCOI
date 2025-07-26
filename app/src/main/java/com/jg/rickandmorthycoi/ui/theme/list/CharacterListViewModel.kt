package com.jg.rickandmorthycoi.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.rickandmorthycoi.domain.model.Character
import com.jg.rickandmorthycoi.domain.usecase.GetCharactersUseCase
import com.jg.rickandmorthycoi.domain.usecase.GetFavoriteIdsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


sealed class CharacterListUiState {
    object Loading : CharacterListUiState()
    data class Success(val characters: List<Character>) : CharacterListUiState()
    data class Error(val message: String) : CharacterListUiState()
}


class CharacterListViewModel(
    private val getCharacters: GetCharactersUseCase,
    private val getFavoriteIds: GetFavoriteIdsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    init {
        fetchCharacters()
    }

    /**
     * Pobiera listę, subskrybuje ulubione i aktualizuje UI.
     */
    fun fetchCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterListUiState.Loading
            try {
                // Pobranie podstawowej listy postaci
                val chars = getCharacters()

                // Połączenie z listą ulubionych ID
                getFavoriteIds()
                    .map { favIds ->
                        chars.map { c ->
                            c.copy(isFavorite = favIds.contains(c.id))
                        }
                    }
                    .collect { updatedList ->
                        _uiState.value = CharacterListUiState.Success(updatedList)
                    }
            } catch (e: Exception) {
                _uiState.value = CharacterListUiState.Error(
                    e.localizedMessage ?: "Nieznany błąd podczas ładowania postaci"
                )
            }
        }
    }
}
