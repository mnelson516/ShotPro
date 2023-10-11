package com.example.composetest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composetest.presentation.model.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    suspend fun getExercises(): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE date = :date")
    suspend fun loadAllByDate(date: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE location = :location")
    suspend fun loadAllByLocation(location: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE range = :range")
    suspend fun loadAllByRange(range: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE name = :name")
    suspend fun loadAllByName(name: String): List<ExerciseEntity>

    @Insert
    suspend fun insertAll(exercises: List<ExerciseEntity>)

}