package com.nelson.shotlogger.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nelson.shotlogger.data.mapToExercise
import com.nelson.shotlogger.domain.ExerciseEntity
import com.nelson.shotlogger.domain.ExerciseOrder
import com.nelson.shotlogger.domain.ExerciseRepository
import com.nelson.shotlogger.presentation.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private var exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.asStateFlow()

    private var getExercisesJob: Job? = null

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.ShowFilters -> {
                _state.value = state.value.copy(
                    showFilters = !state.value.showFilters
                )
            }
            is HistoryEvent.SelectCategory -> {
                if (state.value.currentCategory::class == event.category::class &&
                    state.value.currentCategory == event.category) {
                    return
                }
                _state.value = state.value.copy(
                    currentCategory = event.category
                )
            }
            is HistoryEvent.GetExercises -> {
                    if (state.value.currentFilter::class == event.order::class &&
                        state.value.currentFilter.orderType == event.order.orderType) {
                        return
                    }
                    _state.value = state.value.copy(
                        detailsText = setDetailsString(event.order)
                    )
                    getExercises(event.order)
                }
            is HistoryEvent.InitialExercises -> {
                getExercises(event.order)
                _state.value = state.value.copy(
                    detailsText = "Total Shots",
                )
            }
            is HistoryEvent.SetDetails -> {
                _state.value = state.value.copy(
                    currentDetails = event.exercise,
                )
            }
        }
    }

    private fun getExercises(exerciseOrder: ExerciseOrder) {
        getExercisesJob?.cancel()
        getExercisesJob = exerciseRepository.fetchExercises()
            .map {
                when (exerciseOrder) {
                    is ExerciseOrder.Default -> {
                        it
                    }
                    is ExerciseOrder.Baseline -> {
                        it.filter { exercise -> exercise.location == "Baseline" }
                    }
                    is ExerciseOrder.Center -> {
                        it.filter { exercise -> exercise.location == "Center" }
                    }
                    is ExerciseOrder.Diagonal -> {
                        it.filter { exercise -> exercise.location == "Diagonal" }
                    }
                    is ExerciseOrder.Elbow -> {
                        it.filter { exercise -> exercise.location == "Elbow" }
                    }
                    is ExerciseOrder.Left -> {
                        it.filter { exercise -> exercise.side == "Left" }
                    }
                    is ExerciseOrder.Right -> {
                        it.filter { exercise -> exercise.side == "Right" }
                    }
                    is ExerciseOrder.CloseRange -> {
                        it.filter { exercise -> exercise.range == "Close Range" }
                    }
                    is ExerciseOrder.MidRange -> {
                        it.filter { exercise -> exercise.range == "Mid Range" }
                    }
                    is ExerciseOrder.ThreePointRange -> {
                        it.filter { exercise -> exercise.range == "Three Pointer" }
                    }
                }
            }
            .onEach { exercises ->
                _state.value = state.value.copy(
                    exercises = convertExerciseEntity(exercises),
                    currentFilter = exerciseOrder
                )
            }.launchIn(viewModelScope)
    }

    private fun setDetailsString(exerciseOrder: ExerciseOrder): String {
        return when (exerciseOrder) {
            is ExerciseOrder.Baseline -> "Baseline"
            is ExerciseOrder.Center -> "Center"
            is ExerciseOrder.CloseRange -> "Close Range"
            is ExerciseOrder.Default -> "Default"
            is ExerciseOrder.Diagonal -> "Diagonal"
            is ExerciseOrder.Elbow -> "Elbow"
            is ExerciseOrder.Left -> "Left Side"
            is ExerciseOrder.MidRange -> "Mid Range"
            is ExerciseOrder.Right -> "Right Side"
            is ExerciseOrder.ThreePointRange -> "Three Pointer"
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