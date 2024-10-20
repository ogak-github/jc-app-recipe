package com.example.resepnya.ui.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.resepnya.model.Recipe
import com.example.resepnya.ui.common.Constants
import com.example.resepnya.ui.common.TopAppbar

@Composable
fun RecipeDetailUI(navController: NavController, recipe: Recipe) {

    /*if (recipe == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            content = {
                CircularProgressIndicator()
            },
            contentAlignment = Alignment.Center
        )
    } else {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopAppbar(recipe.value?.name ?: "Undefined") }
        ) {
            Box(modifier = Modifier.padding(it)) {
                RecipeDetailView(recipe.value!!)
            }
        }
    }*/

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppbar(recipe.name) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            RecipeDetailView(recipe)
        }
    }


}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeDetailView(recipe: Recipe) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(Constants.MEDIUM_PADDING)
            .clip(RoundedCornerShape(Constants.MEDIUM_PADDING))
    ) {
        item {
            Card {
                AsyncImage(
                    model = recipe.image, // Image URL
                    contentDescription = recipe.name,
                    alignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        //.padding(Constants.MEDIUM_PADDING)
                        .clip(RoundedCornerShape(Constants.MEDIUM_PADDING)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .padding(Constants.MEDIUM_PADDING)
                        .fillMaxSize(),
                    content = {
                        Column {
                            Text(recipe.name, style = MaterialTheme.typography.headlineSmall)
                            Spacer(modifier = Modifier.height(Constants.LARGE_PADDING))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(Constants.MEDIUM_PADDING))
                                    .background(Color.LightGray)
                            ) {
                                val rows = 2
                                FlowRow(
                                    modifier = Modifier
                                        .padding(Constants.MEDIUM_PADDING)
                                        .fillMaxWidth()
                                        .align(Alignment.TopStart),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalArrangement = Arrangement.Top,
                                    maxItemsInEachRow = rows
                                ) {
                                    RecipeDetail(
                                        recipe.difficulty, "Kesulitan",
                                    )
                                    RecipeDetail(
                                        recipe.cookTimeMinutes.toString() + " min",
                                        "Waktu Masak",
                                    )
                                    RecipeDetail(
                                        recipe.prepTimeMinutes.toString() + " min",
                                        "Waktu Persiapan",

                                        )
                                    RecipeDetail(
                                        recipe.servings.toString() + " porsi",
                                        "Porsi",

                                        )
                                }
                            }

                            Spacer(modifier = Modifier.height(Constants.LARGE_PADDING))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(Constants.MEDIUM_PADDING))
                                    .background(Color.White)
                            ) {
                                RecipeIngredients(recipe.ingredients)
                            }

                            Spacer(modifier = Modifier.height(Constants.LARGE_PADDING))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(Constants.MEDIUM_PADDING))
                                    .background(Color.White)
                            ) {
                                RecipeSteps(recipe.instructions)
                            }

                        }
                    }
                )
            }
        }
    }

}

@Composable
fun RecipeDetail(value: String, label: String) {
    Box(
    ) {
        Row {
            Text(label, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(Constants.SMALL_PADDING))
            Text(": ", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(Constants.SMALL_PADDING))
            Text(value, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun RecipeSteps(steps: List<String>) {
    Column(
        modifier = Modifier.padding(Constants.MEDIUM_PADDING)
    ) {
        Text("Instructions", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(Constants.MEDIUM_PADDING))
        steps.forEach {
            val index = steps.indexOf(it) + 1
            Row(
                modifier = Modifier.padding(Constants.SMALL_PADDING),
                horizontalArrangement = Arrangement.spacedBy(Constants.SMALL_PADDING),
                verticalAlignment = Alignment.Top
            ) {
                Spacer(modifier = Modifier.height(Constants.SMALL_PADDING))
                Text("$index. ", style = MaterialTheme.typography.bodyLarge)
                Text(it, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun RecipeIngredients(ingredients: List<String>) {
    Column(
        modifier = Modifier.padding(Constants.MEDIUM_PADDING)
    ) {
        Text("Ingredients", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(Constants.MEDIUM_PADDING))
        ingredients.forEach {
            val index = ingredients.indexOf(it) + 1
            Row(
                modifier = Modifier.padding(Constants.SMALL_PADDING),
                horizontalArrangement = Arrangement.spacedBy(Constants.SMALL_PADDING),
                verticalAlignment = Alignment.Top
            ) {
                Spacer(modifier = Modifier.height(Constants.SMALL_PADDING))
                Text("$index. ", style = MaterialTheme.typography.bodyLarge)
                Text(it, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}