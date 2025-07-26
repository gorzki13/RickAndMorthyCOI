package com.jg.rickandmorthycoi.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.rickandmorthycoi.domain.model.Character
import com.jg.rickandmorthycoi.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class CharacterListUiState {
    object Loading : CharacterListUiState()
    data class Success(val characters: List<Character>) : CharacterListUiState()
    data class Error(val message: String) : CharacterListUiState()
}

class CharacterListViewModel(
    private val getCharacters: GetCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)
    val uiState: StateFlow<CharacterListUiState> = _uiState

    init {
        fetchCharacters()
    }

    fun fetchCharacters(page: Int = 1) {
        _uiState.value = CharacterListUiState.Loading
        viewModelScope.launch {
            try {
                val list = getCharacters(page)
                _uiState.value = CharacterListUiState.Success(list)
            } catch (e: Exception) {
                _uiState.value = CharacterListUiState.Error(e.localizedMessage ?: "Nieznany błąd")
            }
        }
    }
}