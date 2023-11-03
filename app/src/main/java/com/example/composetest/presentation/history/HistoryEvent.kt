package com.example.composetest.presentation.history

import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.domain.OrderType


sealed class HistoryEvent {
    object ShowFilters: HistoryEvent()
    data class GetExercises(val order: ExerciseOrder, val param: String): HistoryEvent()
    data class InitialExercises(val order: ExerciseOrder): HistoryEvent()
    data class SelectCategory(val category: OrderType): HistoryEvent()

}