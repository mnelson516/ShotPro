package com.nelson.shotlogger.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nelson.shotlogger.domain.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    fun getExercises(): Flow<List<ExerciseEntity>>
    @Insert
    suspend fun insertAll(exercises: List<ExerciseEntity>)

}