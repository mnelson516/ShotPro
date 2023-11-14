package com.nelson.shotlogger.presentation.addworkout

import com.nelson.shotlogger.presentation.model.Exercise

sealed class AddExerciseEvent {

    data class AddExercise(val exercise: Exercise) : AddExerciseEvent()
    data class DeleteExercise(val exercise: Exercise) : AddExerciseEvent()
    data class ShowSaveButton(val showSaveButton: Boolean): AddExerciseEvent()
    data class ShowDialog(val showDialog: Boolean): AddExerciseEvent()
    object ClearExercises: AddExerciseEvent()
    object SaveExercises: AddExerciseEvent()
}
