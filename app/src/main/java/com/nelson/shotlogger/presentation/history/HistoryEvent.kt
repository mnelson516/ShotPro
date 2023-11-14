package com.nelson.shotlogger.presentation.history

import com.nelson.shotlogger.domain.ExerciseOrder
import com.nelson.shotlogger.domain.OrderType
import com.nelson.shotlogger.presentation.model.Exercise


sealed class HistoryEvent {
    object ShowFilters: HistoryEvent()
    data class GetExercises(val order: ExerciseOrder, val param: String): HistoryEvent()
    data class InitialExercises(val order: ExerciseOrder): HistoryEvent()
    data class SelectCategory(val category: OrderType): HistoryEvent()
    data class SetDetails(val exercise: Exercise): HistoryEvent()
}