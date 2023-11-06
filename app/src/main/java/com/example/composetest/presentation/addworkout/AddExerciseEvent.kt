package com.example.composetest.presentation.addworkout

import com.example.composetest.presentation.model.Exercise

sealed class AddExerciseEvent {

    data class AddExercise(val exercise: Exercise) : AddExerciseEvent()
    data class DeleteExercise(val exercise: Exercise) : AddExerciseEvent()
    data class ShowSaveButton(val showSaveButton: Boolean): AddExerciseEvent()
    object ClearExercises: AddExerciseEvent()
    object SaveExercises: AddExerciseEvent()
}
