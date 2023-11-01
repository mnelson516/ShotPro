package com.example.composetest.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "field-goals")
data class FieldGoalDataEntity (
    @PrimaryKey
    val id: Int = 1,
    var totalFieldGoals: Int,
    var totalFieldGoalsMade: Int,
    var rightSideFieldGoals: Int,
    var rightSideFieldGoalsMade: Int,
    var leftSideFieldGoals: Int,
    var leftSideFieldGoalsMade: Int,
    var closeRangeFieldGoals: Int,
    var closeRangeFieldGoalsMade: Int,
    var midRangeFieldGoals: Int,
    var midRangeFieldGoalsMade: Int,
    var threePointFieldGoals: Int,
    var threePointFieldGoalsMade: Int,
    var baseLineFieldGoals: Int,
    var baseLineFieldGoalsMade: Int,
    var diagonalFieldGoals: Int,
    var diagonalFieldGoalsMade: Int,
    var elbowFieldGoals: Int,
    var elbowFieldGoalsMade: Int,
    var centerFieldGoals: Int,
    var centerFieldGoalsMade: Int
)