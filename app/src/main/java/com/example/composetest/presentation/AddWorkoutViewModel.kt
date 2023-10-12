package com.example.composetest.presentation

import androidx.lifecycle.ViewModel
import com.example.composetest.data.mapToEntity
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.presentation.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val exerciseRepository: ExerciseRepository,

) : ViewModel() {

    private val exerciseList = mutableListOf<ExerciseEntity>()

    suspend fun saveExercises() {
        exerciseRepository.insertExercise(exerciseList)
    }
    fun addExercise(exercise: Exercise) {
        exerciseList.add(exercise.mapToEntity())
    }
}