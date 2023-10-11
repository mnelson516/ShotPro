package com.example.composetest.data

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import javax.inject.Inject
import javax.inject.Singleton

interface ExerciseRepository {
    suspend fun fetchExercises() : List<ExerciseEntity>

    suspend fun loadAllByDate(date: String): List<ExerciseEntity>

    suspend fun loadAllByLocation(location: String): List<ExerciseEntity>

    suspend fun loadAllByRange(range: String): List<ExerciseEntity>

    suspend fun loadAllByName(name: String): List<ExerciseEntity>

    suspend fun insertAll(exercises: List<ExerciseEntity>)
}

@Singleton
class ExerciseRepositoryImpl @Inject constructor (
    private val exerciseDao : ExerciseDao
) : ExerciseRepository {


    override suspend fun fetchExercises(): List<ExerciseEntity> = exerciseDao.getExercises()
    override suspend fun loadAllByDate(date: String): List<ExerciseEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllByLocation(location: String): List<ExerciseEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllByRange(range: String): List<ExerciseEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllByName(name: String): List<ExerciseEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(exercises: List<ExerciseEntity>) {
        TODO("Not yet implemented")
    }
}