package com.example.composetest.data

import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepositoryImpl @Inject constructor (
    private val exerciseDao : ExerciseDao
) : ExerciseRepository {

    override fun fetchExercises(): Flow<List<ExerciseEntity>> = exerciseDao.getExercises()
    override suspend fun insertExercise(exercises: List<ExerciseEntity>) {
        exerciseDao.insertAll(exercises)
    }
}