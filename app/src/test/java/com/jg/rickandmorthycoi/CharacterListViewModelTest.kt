package com.jg.rickandmorthycoi.ui.list

import com.jg.rickandmorthycoi.domain.model.Character
import com.jg.rickandmorthycoi.domain.usecase.GetCharactersUseCase
import com.jg.rickandmorthycoi.domain.usecase.GetFavoriteIdsUseCase
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getCharacters: GetCharactersUseCase
    private lateinit var getFavoriteIds: GetFavoriteIdsUseCase
    private lateinit var vm: CharacterListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getCharacters = mockk()
        getFavoriteIds = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchCharacters emits Success when usecases return data`() = runTest {
        val sample = listOf(
            Character(1, "Rick", "url1", isFavorite = false),
            Character(2, "Morty", "url2", isFavorite = false)
        )
        coEvery { getCharacters() } returns sample
        coEvery { getFavoriteIds() } returns flowOf(setOf(2))

        // when
        vm = CharacterListViewModel(getCharacters, getFavoriteIds)
        advanceUntilIdle()

        // then
        val state = vm.uiState.value
        assertTrue(state is CharacterListUiState.Success)
        state as CharacterListUiState.Success
        assertTrue(state.characters.first { it.id == 1 }.isFavorite == false)
        assertTrue(state.characters.first { it.id == 2 }.isFavorite == true)
    }

    @Test
    fun `fetchCharacters emits Error when getCharacters throws`() = runTest {
        // given
        coEvery { getCharacters() } throws RuntimeException("fail")
        coEvery { getFavoriteIds() } returns flowOf(emptySet())

        // when
        vm = CharacterListViewModel(getCharacters, getFavoriteIds)
        advanceUntilIdle()

        // then
        val state = vm.uiState.value
        assertTrue(state is CharacterListUiState.Error)
        assertTrue((state as CharacterListUiState.Error).message.contains("fail"))
    }
}

