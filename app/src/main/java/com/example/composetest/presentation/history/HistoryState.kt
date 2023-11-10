package com.example.composetest.presentation.history

import androidx.paging.compose.LazyPagingItems
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.domain.OrderType
import com.example.composetest.presentation.model.Exercise

data class HistoryState (
    val showFilters: Boolean = false,
    val exercises: List<Exercise> = emptyList(),
    val currentCategory: OrderType = OrderType.Default,
    val currentFilter: ExerciseOrder = ExerciseOrder.Default(OrderType.Default),
    val currentDetails: Exercise? = null,
    val detailsText: String = ""
)