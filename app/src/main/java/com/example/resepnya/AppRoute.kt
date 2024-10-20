package com.example.resepnya

sealed class AppRoute(val route: String) {
    object Main : AppRoute("main_ui")
    object RecipeDetail : AppRoute("recipe_detail_ui")
}