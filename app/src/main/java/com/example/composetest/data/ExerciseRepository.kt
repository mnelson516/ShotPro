package com.example.composetest.data

import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepositoryImpl @Inject constructor (
    private val exerciseDao : ExerciseDao
) : ExerciseRepository {

    override suspend fun fetchExercises(): List<ExerciseEntity> = exerciseDao.getExercises()
    override suspend fun loadAllByDate(date: String): List<ExerciseEntity> = exerciseDao.loadAllByDate(date)
    override suspend fun loadAllByLocation(location: String): List<ExerciseEntity> = exerciseDao.loadAllByLocation(location)
    override suspend fun loadAllByRange(range: String): List<ExerciseEntity> = exerciseDao.loadAllByRange(range)
    override suspend fun loadAllByName(name: String): List<ExerciseEntity> = exerciseDao.loadAllByName(name)
    override suspend fun insertExercise(exercises: List<ExerciseEntity>) {
        exerciseDao.insertAll(exercises)
    }
}