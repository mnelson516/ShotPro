package com.example.composetest.presentation.AddExercise

import com.example.composetest.presentation.model.Exercise

data class AddExercisesState(
    val exercises: List<Exercise> = emptyList(),
    val showPopup: Boolean = false,
    val showSaveButton: Boolean = false
)
