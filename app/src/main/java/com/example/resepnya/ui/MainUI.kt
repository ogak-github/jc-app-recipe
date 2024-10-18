package com.example.resepnya.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resepnya.datasource.Sort
import com.example.resepnya.model.Recipe
import com.example.resepnya.viewmodel.RecipeViewModel
import dagger.hilt.EntryPoint

@Composable

fun MainUI() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Text("Android Learn") }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            RecipeView()
        }

    }
}


@Composable
fun RecipeView() {
    val viewModel: RecipeViewModel = hiltViewModel()
    val recipes: List<Recipe> = viewModel.recipeList.value?.recipes ?: listOf()

    LaunchedEffect(Unit) {
        viewModel.loadRecipes(10, 0, Sort.NAME.name.lowercase())
    }
    if (recipes.isEmpty()) {

        CircularProgressIndicator()

    } else {
        RecipeList(recipes)
    }
}



@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(4.dp)
    ) {
        items(recipes.size) { recipe ->
            RecipeItem(recipes[recipe].name)
        }
    }
}

@Composable
fun RecipeItem(name: String) {
    Text(name)
}