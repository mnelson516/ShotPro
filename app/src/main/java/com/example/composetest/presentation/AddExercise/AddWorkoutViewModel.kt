package com.example.composetest.presentation.AddExercise

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.data.mapToEntity
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.presentation.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _state = mutableStateOf(AddExercisesState())
    val state : State<AddExercisesState> = _state
    fun onEvent(event: AddExerciseEvent) {
        when (event) {
            is AddExerciseEvent.AddExercise -> {
                val newList: MutableList<Exercise> = state.value.exercises.toMutableList()
                newList.add(event.exercise)
                _state.value = state.value.copy(
                    exercises = newList.toList()
                )
            }
            is AddExerciseEvent.DeleteExercise -> {
                val newList: MutableList<Exercise> = state.value.exercises.toMutableList()
                newList.remove(event.exercise)
                _state.value = state.value.copy(
                    exercises = newList.toList()
                )
            }
            is AddExerciseEvent.ShowPopup -> {
                _state.value = state.value.copy(
                    showPopup = event.showPopup
                )
            }
            is AddExerciseEvent.ShowSaveButton -> {
                _state.value = state.value.copy(
                    showSaveButton = event.showSaveButton
                )
            }
            is AddExerciseEvent.SaveExercises -> {
                convertToEntities(state.value.exercises)
                viewModelScope.launch {
                    exerciseRepository.insertExercise(convertToEntities(state.value.exercises))
                }
            }
        }
    }

    private fun convertToEntities(list: List<Exercise>): List<ExerciseEntity> {
        val entityList = mutableListOf<ExerciseEntity>()
        for (exercise in list) {
            entityList.add(exercise.mapToEntity())
        }
        return entityList
    }


}