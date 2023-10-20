package com.example.composetest.domain

import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
     fun fetchExercises() : Flow<List<ExerciseEntity>>
     suspend fun insertExercise(exercises: List<ExerciseEntity>)
}
