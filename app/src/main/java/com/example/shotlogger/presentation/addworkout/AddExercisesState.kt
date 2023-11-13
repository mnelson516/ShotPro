package com.example.shotlogger.presentation.addworkout

import com.example.shotlogger.presentation.model.Exercise

data class AddExercisesState(
    val exercises: List<Exercise> = emptyList(),
    val showSaveButton: Boolean = false,
    val showTipDialog: Boolean = true
)
