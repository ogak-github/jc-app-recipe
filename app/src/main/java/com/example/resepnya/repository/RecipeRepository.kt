package com.example.resepnya.repository

import com.example.resepnya.datasource.IRecipeService
import com.example.resepnya.model.Recipe
import com.example.resepnya.model.Recipes
import javax.inject.Inject

class RecipeRepository @Inject constructor(private var recipeService: IRecipeService) :
    IRecipeService {
    override suspend fun getAllRecipes(limit: Int, skip: Int, sortBy: String): Recipes {
        return recipeService.getAllRecipes(limit, skip, sortBy)
    }

    override suspend fun getRecipeById(id: Int): Recipe {
        return recipeService.getRecipeById(id)
    }

    override suspend fun searchRecipes(query: String): Recipes {
        return recipeService.searchRecipes(query)
    }
}