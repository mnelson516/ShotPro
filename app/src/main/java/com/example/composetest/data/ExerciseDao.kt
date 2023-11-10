package com.example.composetest.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composetest.domain.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    fun getExercises(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE side='Right'")
    fun getExercisesRight(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE side='Left'")
    fun getExercisesLeft(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE range='Close Range'")
    fun getExercisesClose(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE range='Mid Range'")
    fun getExercisesMid(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE range='Three Pointer'")
    fun getExercisesThree(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE location='Baseline'")
    fun getExercisesBaseline(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE location='Diagonal'")
    fun getExercisesDiagonal(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE location='Elbow'")
    fun getExercisesElbow(): PagingSource<Int, ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE location='Center'")
    fun getExercisesCenter(): PagingSource<Int, ExerciseEntity>


    @Insert
    suspend fun insertAll(exercises: List<ExerciseEntity>)

}