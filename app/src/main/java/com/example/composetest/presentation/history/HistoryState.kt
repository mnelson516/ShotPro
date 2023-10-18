package com.example.composetest.presentation.history

import com.example.composetest.presentation.model.Exercise

data class HistoryState (
    val showFilters: Boolean = false,
    val exercises: List<Exercise> = emptyList()
)