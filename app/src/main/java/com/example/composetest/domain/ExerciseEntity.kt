package com.example.composetest.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: LocalDateTime,
    val name: String,
    val shotsMade: Int,
    val totalShots: Int,
    val range: String,
    val location: String,
)