package com.example.composetest.presentation.history

import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.domain.OrderType
import com.example.composetest.presentation.model.Exercise


sealed class HistoryEvent {
    object ShowFilters: HistoryEvent()
    data class GetExercises(val order: ExerciseOrder, val param: String): HistoryEvent()
    data class SelectCategory(val category: OrderType): HistoryEvent()
    data class SetDetails(val exercise: Exercise): HistoryEvent()
}