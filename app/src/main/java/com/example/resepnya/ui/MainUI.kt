package com.example.resepnya.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.resepnya.AppRoute
import com.example.resepnya.LocalNavController
import com.example.resepnya.datasource.Sort
import com.example.resepnya.model.Recipe
import com.example.resepnya.ui.common.Constants
import com.example.resepnya.ui.common.TopAppbar
import com.example.resepnya.viewmodel.RecipeViewModel
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MainUI(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppbar("Resepnya") }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            RecipeView()
        }
    }
}


@Composable
fun RecipeView() {
    val viewModel: RecipeViewModel = hiltViewModel()

    val recipes = viewModel.recipeList.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRecipes(24, 0, Sort.NAME.name.lowercase())
    }

    if (recipes.value == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            content = {
                CircularProgressIndicator()
            },
            contentAlignment = Alignment.Center
        )
    } else {
        RecipeList(recipes.value!!.recipes)
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeList(recipes: List<Recipe>) {
    val itemModifier = Modifier
        .padding(Constants.SMALL_PADDING)
        .height(Constants.CARD_HEIGHT)
        .width(Constants.CARD_WIDTH)
        .clip(RoundedCornerShape(Constants.MEDIUM_PADDING))
        .background(Color.Transparent)
        .fillMaxSize()

    val rows = 2

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            FlowRow(
                modifier = Modifier.padding(Constants.SMALL_PADDING),
                horizontalArrangement = Arrangement.spacedBy(Constants.SMALL_PADDING),
                verticalArrangement = Arrangement.Top,
                maxItemsInEachRow = rows,

                ) {
                recipes.forEach {
                    Box(
                        modifier = itemModifier,
                        contentAlignment = Alignment.Center
                    ) {
                        RecipeItem(it)
                    }
                }
            }
        }
    }

}

@Composable
fun RecipeItem(recipe: Recipe) {
    val navController: NavController = LocalNavController.current
    val viewModel: RecipeViewModel = hiltViewModel()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = Constants.CARD_WIDTH, height = Constants.CARD_HEIGHT)
            .clickable() {
                //viewModel.setRecipeDetail(recipe)
                val jsonData = Uri.encode(Gson().toJson(recipe))
                navController.navigate(AppRoute.RecipeDetail.route + "?v=${jsonData}")
            },
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                content = {
                    AsyncImage(
                        model = recipe.image, // Image URL
                        contentDescription = recipe.name,
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .size(85.dp) // Fixed size for the image
                            .padding(Constants.MEDIUM_PADDING)
                            .clip(RoundedCornerShape(Constants.MEDIUM_PADDING)),
                        contentScale = ContentScale.Crop
                    )
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart,
                content = {
                    Column {
                        Text(recipe.name, style = MaterialTheme.typography.titleSmall)
                        Text(
                            recipe.difficulty, style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            )
        }
    }

}