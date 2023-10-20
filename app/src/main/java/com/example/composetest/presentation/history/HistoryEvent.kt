package com.example.composetest.presentation.history

import com.example.composetest.domain.ExerciseOrder


sealed class HistoryEvent {
    object ShowFilters: HistoryEvent()
    data class GetExercises(val order: ExerciseOrder, val param: String): HistoryEvent()

}