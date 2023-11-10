package com.example.composetest.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.composetest.data.mapToExercise
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.domain.GetAllExercisesPagerUseCase
import com.example.composetest.domain.OrderType
import com.example.composetest.presentation.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getAllExercisesPagerUseCase: GetAllExercisesPagerUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.asStateFlow()

    val exercisesPagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .call()
        .cachedIn(viewModelScope)

    val rightPagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callRight()
        .cachedIn(viewModelScope)

    val leftPagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callLeft()
        .cachedIn(viewModelScope)

    val closePagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callClose()
        .cachedIn(viewModelScope)

    val midPagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callMid()
        .cachedIn(viewModelScope)

    val threePagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callThree()
        .cachedIn(viewModelScope)

    val baselinePagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callBaseline()
        .cachedIn(viewModelScope)

    val diagonalPagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callDiagonal()
        .cachedIn(viewModelScope)

    val elbowPagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callElbow()
        .cachedIn(viewModelScope)

    val centerPagingSource: Flow<PagingData<ExerciseEntity>> = getAllExercisesPagerUseCase
        .callCenter()
        .cachedIn(viewModelScope)


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
                }
            is HistoryEvent.SetDetails -> {
                _state.value = state.value.copy(
                    currentDetails = event.exercise,
                )
            }
        }
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