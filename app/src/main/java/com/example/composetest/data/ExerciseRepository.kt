package com.example.composetest.data

import androidx.paging.PagingSource
import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.domain.ExerciseRepository
import com.example.composetest.domain.FieldGoalDataEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ExerciseRepositoryImpl @Inject constructor (
    private val exerciseDao : ExerciseDao,
    private val fieldGoalDao : FieldGoalDao
) : ExerciseRepository {

    override fun fetchExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercises()
    override fun fetchRightSideExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesRight()
    override fun fetchLeftSideExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesLeft()
    override fun fetchCloseRangeExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesClose()
    override fun fetchMidRangeExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesMid()
    override fun fetchThreePointExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesThree()
    override fun fetchBaselineExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesBaseline()
    override fun fetchDiagonalExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesDiagonal()
    override fun fetchCenterExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesCenter()
    override fun fetchElbowExercises(): PagingSource<Int, ExerciseEntity> = exerciseDao.getExercisesElbow()
    override suspend fun fetchFieldGoalData(): FieldGoalDataEntity? = fieldGoalDao.getFieldGoalData()
    override suspend fun insertExercise(exercises: List<ExerciseEntity>) {
        exerciseDao.insertAll(exercises)
    }
    override suspend fun insertFieldGoalData(data: FieldGoalDataEntity) {
        fieldGoalDao.upsertFieldGoalData(data)
    }
}