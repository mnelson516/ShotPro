package com.nelson.shotlogger.presentation.addworkout

import com.nelson.shotlogger.presentation.model.Exercise

data class AddExercisesState(
    val exercises: List<Exercise> = emptyList(),
    val showSaveButton: Boolean = false,
    val showTipDialog: Boolean = true
)
