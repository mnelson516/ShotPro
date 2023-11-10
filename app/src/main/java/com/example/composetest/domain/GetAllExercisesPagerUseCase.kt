package com.example.composetest.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllExercisesPagerUseCase@Inject constructor(
    private val exerciseRepository: ExerciseRepository
) {
    fun call(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchExercises()
    }.flow

    fun callRight(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchRightSideExercises()
    }.flow

    fun callLeft(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchLeftSideExercises()
    }.flow

    fun callClose(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchCloseRangeExercises()
    }.flow

    fun callMid(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchMidRangeExercises()
    }.flow

    fun callThree(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchThreePointExercises()
    }.flow

    fun callBaseline(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchBaselineExercises()
    }.flow

    fun callDiagonal(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchDiagonalExercises()
    }.flow

    fun callElbow(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchElbowExercises()
    }.flow

    fun callCenter(): Flow<PagingData<ExerciseEntity>> = Pager(
        PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    ) {
        exerciseRepository.fetchCenterExercises()
    }.flow
}