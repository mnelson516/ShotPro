package com.example.composetest.domain

interface ExerciseRepository {
    suspend fun fetchExercises() : List<ExerciseEntity>

    suspend fun loadAllByDate(date: String): List<ExerciseEntity>

    suspend fun loadAllByLocation(location: String): List<ExerciseEntity>

    suspend fun loadAllByRange(range: String): List<ExerciseEntity>

    suspend fun loadAllByName(name: String): List<ExerciseEntity>

    suspend fun insertExercise(exercises: List<ExerciseEntity>)
}
