package com.example.composetest.database

import androidx.room.Dao
import androidx.room.Query
import com.example.composetest.model.Exercise
import java.util.concurrent.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises")
    fun getNotes(): List<ExerciseEntity>

}