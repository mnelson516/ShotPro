package com.nelson.shotlogger.domain

import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
     fun fetchExercises() : Flow<List<ExerciseEntity>>
     suspend fun fetchFieldGoalData(): FieldGoalDataEntity?
     suspend fun insertExercise(exercises: List<ExerciseEntity>)
     suspend fun insertFieldGoalData(data: FieldGoalDataEntity)
}
