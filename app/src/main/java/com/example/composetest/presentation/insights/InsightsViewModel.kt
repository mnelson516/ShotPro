package com.example.composetest.presentation.insights

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.data.mapToFieldGoal
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.domain.FieldGoalDataEntity
import com.example.composetest.presentation.model.FieldGoalData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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

    }

    fun getFieldGoals() {
        CoroutineScope(Dispatchers.IO).launch {
            val currFieldGoalData: FieldGoalData? = exerciseRepository.fetchFieldGoalData()
                ?.mapToFieldGoal()
            _state.value = state.value.copy(
                data = currFieldGoalData
            )
        }

    }

}