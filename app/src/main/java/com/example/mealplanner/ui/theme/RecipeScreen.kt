package com.example.mealplanner.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(){
    var showAddMealForm by remember{mutableStateOf(false)}
    var meals by remember { mutableStateOf(listOf<Recipe>()) }// this line will stores the list of meals

        Scaffold(
            topBar = {
            TopAppBar(title = {Text("Meal Planner", fontStyle = FontStyle.Italic)}, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan))
        })  {paddingValues ->
            Column(
                modifier = Modifier.statusBarsPadding().fillMaxSize().padding(paddingValues = paddingValues).padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Button(onClick = {
                    showAddMealForm = true

                }) {
                    Text("Add Meal")
                }
                Spacer(modifier = Modifier.weight(1f))
                if (showAddMealForm){
                    AddMeal(
                        hideForm  = {showAddMealForm = false},
                        onMealAdded = {mealName, mealType ->
                            val newMeal = Recipe(
                                id = meals.size +1 ,
                                mealName = mealName,
                                selectedMealType = mealType,
                                isCooked = false
                            )
                            meals = meals + newMeal
                            showAddMealForm = false
                        }
                    )
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(meals){meal ->
                        if (meal.isCooked){
                            MealEditor(meal = meal,
                                onEditComplete = {editName, editType ->
                                    meals = meals.map {
                                        if(it.id == meal.id){
                                            it.copy(
                                                mealName = editName ,
                                                isCooked = false
                                            )
                                        }else{
                                            it
                                        }
                                    }
                                })
                        }
                        RecipeList( meal = meal , onEditClick = {
                            meals = meals.map {
                                it.copy(isCooked = it.id == meal.id)
                            }
                        } , onDeleteClick = {
                            meals = meals -meal
                        })
                    }
                }

            }
        }


    }

@Composable
fun RecipeList(
    meal : Recipe,
    onEditClick :()-> Unit,
    onDeleteClick :()->Unit
){
    Row(
        modifier = Modifier.padding(8.dp).fillMaxWidth().border(
            border = BorderStroke(2.dp , Color.Black)
            , shape = RoundedCornerShape(20.dp)
        )
    ){
        Text(text = meal.mealName , modifier = Modifier.padding(8.dp))
        Text(text = meal.selectedMealType , modifier = Modifier.padding(8.dp))
    }
    Row(modifier = Modifier.padding(8.dp)) {
        IconButton(onClick = onEditClick) {
            Icon(imageVector = Icons.Default.Edit , contentDescription = null)
        }
        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeListPreview(){
    RecipeList(Recipe(1, "Pizza", "Lunch", false), {} ,{})
}

@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview(){
    RecipeScreen()
}