package com.jg.rickandmorthycoi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jg.rickandmorthycoi.ui.theme.detail.CharacterDetailScreen
import com.jg.rickandmorthycoi.ui.detail.CharacterDetailViewModel

import com.jg.rickandmorthycoi.ui.list.CharacterListScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(Screen.List.route) {
            CharacterListScreen(
                onCharacterClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
            val detailVm: CharacterDetailViewModel = getViewModel(
                parameters = { parametersOf(characterId) }
            )
            CharacterDetailScreen(
                characterId = characterId,
                onBack = { navController.popBackStack() },
                viewModel = detailVm
            )
        }}
}