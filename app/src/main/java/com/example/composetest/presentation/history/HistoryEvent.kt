package com.example.composetest.presentation.history

import com.example.composetest.presentation.model.Exercise

sealed class HistoryEvent {
    object ShowFilters: HistoryEvent()
    data class GetExerciseByRange(val range: String): HistoryEvent()

}