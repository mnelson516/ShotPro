package com.example.shotlogger.presentation.history

import com.example.shotlogger.domain.ExerciseOrder
import com.example.shotlogger.domain.OrderType
import com.example.shotlogger.presentation.model.Exercise

data class HistoryState (
    val showFilters: Boolean = false,
    val exercises: List<Exercise> = emptyList(),
    val currentCategory: OrderType = OrderType.Default,
    val currentFilter: ExerciseOrder = ExerciseOrder.Default(OrderType.Default),
    val currentDetails: Exercise? = null,
    val detailsText: String = ""
)