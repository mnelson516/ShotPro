package com.example.shotlogger.presentation.history

import com.example.shotlogger.domain.ExerciseOrder
import com.example.shotlogger.domain.OrderType
import com.example.shotlogger.presentation.model.Exercise


sealed class HistoryEvent {
    object ShowFilters: HistoryEvent()
    data class GetExercises(val order: ExerciseOrder, val param: String): HistoryEvent()
    data class InitialExercises(val order: ExerciseOrder): HistoryEvent()
    data class SelectCategory(val category: OrderType): HistoryEvent()
    data class SetDetails(val exercise: Exercise): HistoryEvent()
}