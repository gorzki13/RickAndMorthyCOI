package com.jg.rickandmorthycoi.navigation

sealed class Screen(val route: String) {
    object List : Screen("list")
    object Detail : Screen("detail/{characterId}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}