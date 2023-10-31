package com.example.composetest.presentation.insights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.domain.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsightsViewModel @Inject constructor(
    private var exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(InsightsState())
    val state = _state.asStateFlow()

    fun onEvent(event: InsightsEvent) {
       // TODO Add Events
    }

    fun getFieldGoals() {
        viewModelScope.launch {
            state.value.copy(
                data = exerciseRepository.fetchFieldGoalData().first()
            )
        }

    }

}