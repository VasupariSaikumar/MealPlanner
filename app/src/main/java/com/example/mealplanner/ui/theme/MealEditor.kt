package com.example.mealplanner.ui.theme

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MealEditor(
    meal : Recipe ,
    onEditComplete : (editName : String , editType : String)-> Unit

){
    var editName by remember { mutableStateOf(meal.mealName) }
    var editType by remember { mutableStateOf(meal.selectedMealType) }

    Row(modifier = Modifier.fillMaxWidth()) {
        BasicTextField(value = meal.mealName ,
            onValueChange = {meal.mealName = it},
            singleLine = true ,
            modifier = Modifier.padding(all = 8.dp))
        BasicTextField(value = meal.selectedMealType ,
            onValueChange = {meal.selectedMealType = it},
            modifier = Modifier.padding(all = 8.dp))
        Button(onClick = {
            if (editName.isNotBlank() && editType.isNotBlank()) {
                onEditComplete(editName, editType)
            }
        }) {
            Text("Save")
        }
    }
}