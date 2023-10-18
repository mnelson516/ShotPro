package com.example.composetest.presentation.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.data.mapToExercise
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.presentation.AddExercise.AddExerciseEvent
import com.example.composetest.presentation.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private var exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state : State<HistoryState> = _state

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.ShowFilters -> {
                _state.value = state.value.copy(
                    showFilters = !state.value.showFilters
                )
            }
            is HistoryEvent.GetExerciseByRange -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        exercises = convertExerciseEntity(exerciseRepository.loadAllByRange(event.range))
                    )
                }
            }
        }
    }

    private fun convertExerciseEntity(list: List<ExerciseEntity>) : List<Exercise> {
        val returnList = mutableListOf<Exercise>()
        for (exercise in list) {
            returnList.add(exercise.mapToExercise())
        }
        return returnList
    }

}