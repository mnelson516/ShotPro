package com.example.composetest.data

import com.example.composetest.domain.ExerciseEntity
import com.example.composetest.presentation.model.Exercise

fun Exercise.mapToEntity() : ExerciseEntity {
    return ExerciseEntity(
        0,
        this.date,
        this.name,
        this.shotsMade,
        this.totalShots,
        this.range,
        this.location
    )
}

fun ExerciseEntity.mapToExercise() : Exercise {
    return Exercise(
        this.date,
        this.name,
        this.shotsMade,
        this.totalShots,
        this.range,
        this.location
    )
}