package com.jg.rickandmorthycoi.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jg.rickandmorthycoi.domain.model.CharacterDetail
import com.jg.rickandmorthycoi.domain.usecase.GetCharacterDetailsUseCase
import com.jg.rickandmorthycoi.domain.usecase.GetFavoriteIdsUseCase
import com.jg.rickandmorthycoi.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

sealed class CharacterDetailUiState {
    object Loading : CharacterDetailUiState()
    data class Success(val detail: CharacterDetail, val isFavorite: Boolean) : CharacterDetailUiState()
    data class Error(val message: String) : CharacterDetailUiState()
}

class CharacterDetailViewModel(
    private val getDetails: GetCharacterDetailsUseCase,
    private val getFavoriteIds: GetFavoriteIdsUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CharacterDetailUiState>(CharacterDetailUiState.Loading)
    val uiState: StateFlow<CharacterDetailUiState> = _uiState

    fun init(id: Int) {
        _uiState.value = CharacterDetailUiState.Loading
        viewModelScope.launch {
            try {
                val detail = getDetails(id)
                val fav = toggleFavorite.repository.getFavoriteIds().first().contains(id)
                _uiState.value = CharacterDetailUiState.Success(detail, fav)
            } catch (e: Exception) {
                _uiState.value = CharacterDetailUiState.Error(e.localizedMessage ?: "Błąd sieci")
            }
        }
    }

    fun onToggleFavorite() {
        viewModelScope.launch {
            when (val state = _uiState.value) {
                is CharacterDetailUiState.Success -> {
                    toggleFavorite(state.detail.id)
                    val newFav = !_uiState.value.let { (it as CharacterDetailUiState.Success).isFavorite }
                    _uiState.value = state.copy(isFavorite = newFav)
                }
                else -> Unit
            }
        }
    }
}
