package com.example.composetest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composetest.domain.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    suspend fun getExercises(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE date = :date")
    suspend fun loadAllByDate(date: String): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE location = :location")
    suspend fun loadAllByLocation(location: String): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE range = :range")
    suspend fun loadAllByRange(range: String): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM exercises WHERE name = :name")
    suspend fun loadAllByName(name: String): Flow<List<ExerciseEntity>>

    @Insert
    suspend fun insertAll(exercises: Flow<List<ExerciseEntity>>)

}