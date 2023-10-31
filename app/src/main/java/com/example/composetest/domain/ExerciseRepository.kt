package com.example.composetest.domain

import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
     fun fetchExercises() : Flow<List<ExerciseEntity>>
     fun fetchFieldGoalData(): Flow<FieldGoalData>
     suspend fun insertExercise(exercises: List<ExerciseEntity>)
     suspend fun insertFieldGoalData(data: FieldGoalData)
}
