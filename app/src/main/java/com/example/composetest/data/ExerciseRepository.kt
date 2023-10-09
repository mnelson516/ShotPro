package com.example.composetest.data

import androidx.lifecycle.LiveData
import javax.inject.Inject
import javax.inject.Singleton

interface ExerciseRepository {
    fun fetchExercises() : List<ExerciseEntity>
}

@Singleton
class ExerciseRepositoryImpl @Inject constructor (
    private val exerciseDao : ExerciseDao
) : ExerciseRepository {


    override fun fetchExercises(): List<ExerciseEntity> = exerciseDao.getExercises()
}