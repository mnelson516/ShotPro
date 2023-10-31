package com.example.composetest.data

import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.domain.FieldGoalData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepositoryImpl @Inject constructor (
    private val exerciseDao : ExerciseDao,
    private val fieldGoalDao : FieldGoalDao
) : ExerciseRepository {

    override fun fetchExercises(): Flow<List<ExerciseEntity>> = exerciseDao.getExercises()
    override fun fetchFieldGoalData(): Flow<FieldGoalData> = fieldGoalDao.getFieldGoalData()

    override suspend fun insertExercise(exercises: List<ExerciseEntity>) {
        exerciseDao.insertAll(exercises)
    }
    override suspend fun insertFieldGoalData(data: FieldGoalData) {
        fieldGoalDao.upsertFieldGoalData(data)
    }
}