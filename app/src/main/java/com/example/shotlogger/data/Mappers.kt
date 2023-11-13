package com.example.shotlogger.data

import com.example.shotlogger.domain.ExerciseEntity
import com.example.shotlogger.domain.FieldGoalDataEntity
import com.example.shotlogger.presentation.model.Exercise
import com.example.shotlogger.presentation.model.FieldGoalData

fun Exercise.mapToEntity() : ExerciseEntity {
    return ExerciseEntity(
        0,
        this.date,
        this.name,
        this.side,
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
        this.side,
        this.shotsMade,
        this.totalShots,
        this.range,
        this.location
    )
}

fun FieldGoalDataEntity.mapToFieldGoal() : FieldGoalData {
    return FieldGoalData(
        this.totalFieldGoals,
        this.totalFieldGoalsMade,
        this.rightSideFieldGoals,
        this.rightSideFieldGoalsMade,
        this.leftSideFieldGoals,
        this.leftSideFieldGoalsMade,
        this.closeRangeFieldGoals,
        this.closeRangeFieldGoalsMade,
        this.midRangeFieldGoals,
        this.midRangeFieldGoalsMade,
        this.threePointFieldGoals,
        this.threePointFieldGoalsMade,
        this.baseLineFieldGoals,
        this.baseLineFieldGoalsMade,
        this.diagonalFieldGoals,
        this.diagonalFieldGoalsMade,
        this.elbowFieldGoals,
        this.elbowFieldGoalsMade,
        this.centerFieldGoals,
        this.centerFieldGoalsMade
    )
}