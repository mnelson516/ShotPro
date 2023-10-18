package com.example.composetest.presentation.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.composetest.domain.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private var exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state : State<HistoryState> = _state

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.ShowFilters -> {
                _state.value = state.value.copy(
                    showFilters = !state.value.showFilters
                )
            }
        }
    }

}