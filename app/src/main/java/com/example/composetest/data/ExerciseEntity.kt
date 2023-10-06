package com.example.composetest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val name: String,
    val shotsMade: Int,
    val totalShots: Int,
    val range: String,
    val location: String,
)