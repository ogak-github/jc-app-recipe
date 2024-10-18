package com.example.resepnya.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.resepnya.model.Recipes
import com.example.resepnya.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(val repo: RecipeRepository) : ViewModel() {

    private val _recipeList = MutableLiveData<Recipes>()
    val recipeList: LiveData<Recipes> = _recipeList

    suspend fun loadRecipes(limit: Int, skip: Int, sortBy: String): Recipes {
        return withContext(Dispatchers.IO) {
            // Perform the network request or database call on the IO dispatcher
            val recipes = repo.getAllRecipes(limit, skip, sortBy)

            // Update LiveData or state; this will be thread-safe and observe the architectural guidelines
            withContext(Dispatchers.Main) {
                _recipeList.value = recipes
            }

            // Return the loaded recipes
            recipes
        }
    }
}