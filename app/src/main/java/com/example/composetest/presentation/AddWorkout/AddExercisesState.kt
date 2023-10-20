package com.example.composetest.presentation.AddWorkout

import com.example.composetest.presentation.model.Exercise

data class AddExercisesState(
    val exercises: List<Exercise> = emptyList(),
    val showSaveButton: Boolean = false
)
