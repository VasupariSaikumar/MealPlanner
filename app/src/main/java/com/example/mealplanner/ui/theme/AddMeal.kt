package com.example.mealplanner.ui.theme

import android.health.connect.datatypes.MealType
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMeal(hideForm : ()-> Unit) {
    var oValue by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedMealType by remember { mutableStateOf("") }


    Column(modifier = Modifier.fillMaxSize())
    {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 36.dp),
            value = oValue,
            onValueChange = { oValue = it },
            label = { Text("Add Meal") },
            singleLine = true,

            )
        Spacer(modifier = Modifier.padding(8.dp))
         ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .menuAnchor()
                    .padding(start = 8.dp, end = 8.dp, top = 4.dp),
                readOnly = true, // this will ensure that user just selects and dont types
                value = selectedMealType,
                onValueChange = {},
                label = { Text("Cooking Schedule") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                singleLine = true
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                listOf("Breakfast", "Lunch", "Dinner").forEach { selection ->
                    DropdownMenuItem(
                        text = { Text(selection) },
                        onClick = {
                            selectedMealType = selection
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
            Spacer(modifier = Modifier.padding(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp)
                    .weight(1f),
                Arrangement.SpaceBetween
            ) {
                Button(onClick = hideForm) {
                    Text("Cancel")
                }
                Button(onClick = { hideForm() }) {
                    Text("Save")

            }
        }
    }
}
/* i placed the row and buttons in the exposedDropDownMenu so the buttons and dropdown were overlapping each other
,after is separated both the issue has fixed
 */
/*
@Preview(showBackground = true)
@Composable
fun AddMealPreview(){
    AddMeal({})
}

 */