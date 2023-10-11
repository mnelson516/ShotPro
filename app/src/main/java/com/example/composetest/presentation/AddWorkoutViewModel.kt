package com.example.composetest.presentation

import androidx.lifecycle.ViewModel
import com.example.composetest.data.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {


}