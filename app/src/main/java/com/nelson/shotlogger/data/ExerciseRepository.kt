package com.nelson.shotlogger.data

import com.nelson.shotlogger.domain.ExerciseEntity
import com.nelson.shotlogger.domain.ExerciseRepository
import com.nelson.shotlogger.domain.FieldGoalDataEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepositoryImpl @Inject constructor (
    private val exerciseDao : ExerciseDao,
    private val fieldGoalDao : FieldGoalDao
) : ExerciseRepository {

    override fun fetchExercises(): Flow<List<ExerciseEntity>> = exerciseDao.getExercises()
    override suspend fun fetchFieldGoalData(): FieldGoalDataEntity? = fieldGoalDao.getFieldGoalData()

    override suspend fun insertExercise(exercises: List<ExerciseEntity>) {
        exerciseDao.insertAll(exercises)
    }
    override suspend fun insertFieldGoalData(data: FieldGoalDataEntity) {
        fieldGoalDao.upsertFieldGoalData(data)
    }
}