package com.example.resepnya.datasource

import com.example.resepnya.model.Recipe
import com.example.resepnya.model.Recipes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRecipeService {
    @GET("recipes")
    suspend fun getAllRecipes(
        @Query("limit") limit: Int,
        @Query("skip") skip: Int,
        @Query("sortBy") sortBy: String
    ): Recipes

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe

    @GET("recipes/search")
    suspend fun searchRecipes(
        @Query("q") query: String
    ): Recipes
}

enum class Sort {
    NAME, RATING
}