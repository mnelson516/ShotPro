package com.example.composetest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composetest.presentation.model.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    fun getExercises(): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE date = :date")
    fun loadAllByDate(date: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE location = :location")
    fun loadAllByLocation(location: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE range = :range")
    fun loadAllByRange(range: String): List<ExerciseEntity>

    @Query("SELECT * FROM exercises WHERE name = :name")
    fun loadAllByName(name: String): List<ExerciseEntity>

    @Insert
    fun insertAll(vararg exercises: ExerciseEntity)

}