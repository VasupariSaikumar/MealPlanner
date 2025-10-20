package com.example.mealplanner.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(){
var showAddMealForm by remember{mutableStateOf(false)}
    val meals = mutableStateOf(listOf<Recipe>())
        Scaffold(topBar = {
            TopAppBar(title = {Text("Meal Planner", fontStyle = FontStyle.Italic)}, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan))
        }) {paddingValues ->
            Column(
                modifier = Modifier.statusBarsPadding().fillMaxSize().padding(paddingValues = paddingValues).padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Button(onClick = { showAddMealForm = true }) {
                    Text("Add Meal")
                }
                Spacer(modifier = Modifier.weight(1f))
                if (showAddMealForm){
                    AddMeal(
                        hideForm  = {showAddMealForm = false}
                    )
                }
                LazyColumn(modifier = Modifier.fillMaxSize()) {

                }

            }
        }


    }

@Composable
fun displayList(){

}



@Preview(showBackground = true)
@Composable
fun RecipeScreenPreview(){
    RecipeScreen()
}