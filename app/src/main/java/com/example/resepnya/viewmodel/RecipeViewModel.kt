package com.example.resepnya.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resepnya.model.Recipe
import com.example.resepnya.model.Recipes
import com.example.resepnya.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(val repo: RecipeRepository) : ViewModel() {

    private val _recipeList = MutableLiveData<Recipes>()
    val recipeList: LiveData<Recipes> = _recipeList

    var selectedRecipe = mutableStateOf<Recipe?>(null)


    suspend fun loadRecipes(limit: Int, skip: Int, sortBy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repo.getAllRecipes(limit, skip, sortBy)
            _recipeList.postValue(result)
        }
    }

    fun setRecipeDetail(recipe: Recipe) {
        selectedRecipe.value = recipe
    }
}