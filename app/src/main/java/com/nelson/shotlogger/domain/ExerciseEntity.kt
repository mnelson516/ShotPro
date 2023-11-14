package com.nelson.shotlogger.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: LocalDateTime,
    val name: String,
    var side: String,
    val shotsMade: Int,
    val totalShots: Int,
    val range: String,
    val location: String,
)