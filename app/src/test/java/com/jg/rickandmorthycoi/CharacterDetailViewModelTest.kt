// src/test/java/com/jg/rickandmorthycoi/domain/usecase/ToggleFavoriteUseCaseTest.kt
package com.jg.rickandmorthycoi.domain.usecase

import com.jg.rickandmorthycoi.data.repository.FavoriteRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class ToggleFavoriteUseCaseTest {

    /**
     * Proste in-memory implementacja FavoriteRepository
     * do testów bez zewnętrznych zależności.
     */
    class InMemoryFavRepo : FavoriteRepository {
        private val set = mutableSetOf<Int>()

        override fun getFavoriteIds() = flowOf(set.toSet())

        override suspend fun toggleFavorite(id: Int) {
            if (!set.remove(id)) set += id
        }

        override suspend fun isFavorite(id: Int) = set.contains(id)
    }

    @Test
    fun `toggleFavorite adds and removes id`() = runBlocking {
        // given
        val repo = InMemoryFavRepo()
        val uc = ToggleFavoriteUseCase(repo)

        // initially empty
        assertFalse(repo.isFavorite(7))

        // when toggling on
        uc.invoke(7)
        val afterAdd = repo.getFavoriteIds().first()
        assertTrue("ID 7 should be added", afterAdd.contains(7))

        // when toggling off
        uc.invoke(7)
        val afterRemove = repo.getFavoriteIds().first()
        assertFalse("ID 7 should be removed", afterRemove.contains(7))
    }
}
