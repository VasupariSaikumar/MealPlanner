package com.example.mealplanner.ui.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MealViewModel : ViewModel() {
    private val _meals = mutableStateOf(emptyList<Recipe>())
    val meals: State<List<Recipe>> = _meals
    fun addMeal(name: String, type: String) {
        val newMeal = Recipe(
            id = _meals.value.size + 1, // Simple unique ID
            mealName = name,
            selectedMealType = type,
            isCooked = false
        )
        _meals.value = _meals.value + newMeal
    }

    fun deleteMeal(meal: Recipe) {
        _meals.value = _meals.value - meal
    }

    fun toggleEditMode(mealId: Int) {
        _meals.value = _meals.value.map {
            it.copy(isCooked = if (it.id == mealId) !it.isCooked else false)
        }
    }

    fun editMeal(mealId: Int, newName: String, newType: String) {
        _meals.value = _meals.value.map {
            if (it.id == mealId) {
                it.copy(
                    mealName = newName,
                    selectedMealType = newType,
                    isCooked = false // Turn editing off after saving
                )
            } else {
                it
            }
        }
    }
}
