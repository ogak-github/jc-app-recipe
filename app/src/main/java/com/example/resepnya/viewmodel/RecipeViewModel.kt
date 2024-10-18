package com.example.resepnya.viewmodel

import androidx.compose.ui.util.fastReduce
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resepnya.datasource.Sort
import com.example.resepnya.model.Recipes
import com.example.resepnya.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(val repo: RecipeRepository) : ViewModel() {

    private val _recipeList = MutableLiveData<Recipes>()
    val recipeList: LiveData<Recipes> = _recipeList

    suspend fun loadRecipes(limit: Int, skip: Int, sortBy: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAllRecipes(limit, skip, sortBy).let {
                _recipeList.postValue(it)
            }

        }
        recipeList.value?.recipes?.forEach {
            println(it.name)
        }
    }


}