package com.example.composetest.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.data.mapToExercise
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.domain.OrderType
import com.example.composetest.presentation.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private var exerciseRepository: ExerciseRepository,
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
            is HistoryEvent.GetExercises -> {
                    if (state.value.currentFilter::class == event.order::class &&
                        state.value.currentFilter.orderType == event.order.orderType) {
                        return
                    }
                    getExercises(event.order, param = event.param)
                }
            }
        }

    private fun getExercises(exerciseOrder: ExerciseOrder, param: String) {

        getExercisesJob?.cancel()
        getExercisesJob = exerciseRepository.fetchExercises()
            .map {
                when (exerciseOrder) {
                    is ExerciseOrder.Date -> {
                        it.filter { exercise -> exercise.date.toString() == param }
                    }
                    is ExerciseOrder.Default -> {
                        it
                    }
                    is ExerciseOrder.Location -> {
                        it.filter { exercise -> exercise.location == param }
                    }
                    is ExerciseOrder.Range -> {
                        it.filter { exercise -> exercise.range == param }
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


    private fun convertExerciseEntity(list: List<ExerciseEntity>) : List<Exercise> {
        val returnList = mutableListOf<Exercise>()
        for (exercise in list) {
            returnList.add(exercise.mapToExercise())
        }
        return returnList
    }
}