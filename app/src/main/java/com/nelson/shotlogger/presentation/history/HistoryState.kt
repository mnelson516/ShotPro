package com.nelson.shotlogger.presentation.history

import com.nelson.shotlogger.domain.ExerciseOrder
import com.nelson.shotlogger.domain.OrderType
import com.nelson.shotlogger.presentation.model.Exercise

data class HistoryState (
    val showFilters: Boolean = false,
    val exercises: List<Exercise> = emptyList(),
    val currentCategory: OrderType = OrderType.Default,
    val currentFilter: ExerciseOrder = ExerciseOrder.Default(OrderType.Default),
    val currentDetails: Exercise? = null,
    val detailsText: String = ""
)