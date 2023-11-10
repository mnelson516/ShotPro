package com.example.composetest.domain

import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
     fun fetchExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchRightSideExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchLeftSideExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchCloseRangeExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchMidRangeExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchThreePointExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchBaselineExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchDiagonalExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchCenterExercises() : PagingSource<Int, ExerciseEntity>
     fun fetchElbowExercises() : PagingSource<Int, ExerciseEntity>
     suspend fun fetchFieldGoalData(): FieldGoalDataEntity?
     suspend fun insertExercise(exercises: List<ExerciseEntity>)
     suspend fun insertFieldGoalData(data: FieldGoalDataEntity)
}
