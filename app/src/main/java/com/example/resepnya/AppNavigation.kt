package com.example.resepnya

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.resepnya.model.Recipe
import com.example.resepnya.ui.MainUI
import com.example.resepnya.ui.recipe.RecipeDetailUI
import com.google.gson.Gson

val LocalNavController = compositionLocalOf<NavController> { error("No NavController provided") }

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = AppRoute.Main.route) {
            composable(route = AppRoute.Main.route) {
                MainUI(navController)
            }
            composable(
                route = "${AppRoute.RecipeDetail.route}?v={recipe}",
                arguments = listOf(
                    navArgument("recipe") {
                        type = NavType.StringType
                    }
                )
            ) {
                val recipeJson = it.arguments?.getString("recipe")
                val recipe = Gson().fromJson(recipeJson, Recipe::class.java)
                RecipeDetailUI(navController, recipe = recipe)
            }
        }
    }
}