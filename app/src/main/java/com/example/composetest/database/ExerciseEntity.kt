package com.example.composetest.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey
    val date: String,
    val name: String,
    val shotsMade: Int,
    val totalShots: Int,
    val range: String,
    val location: String,
)