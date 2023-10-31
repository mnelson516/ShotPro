package com.example.composetest.presentation.insights

import com.example.composetest.domain.FieldGoalData

data class InsightsState(
    val data: FieldGoalData = FieldGoalData(
        totalFieldGoals = 0,
        totalFieldGoalsMade = 0,
        closeRangeFieldGoals = 0,
        closeRangeFieldGoalsMade = 0,
        midRangeFieldGoals = 0,
        midRangeFieldGoalsMade = 0,
        threePointFieldGoals = 0,
        threePointFieldGoalsMade = 0,
        baseLineFieldGoals = 0,
        baseLineFieldGoalsMade = 0,
        elbowFieldGoals = 0,
        elbowFieldGoalsMade = 0,
        diagonalFieldGoals = 0,
        diagonalFieldGoalsMade = 0,
        centerFieldGoals = 0,
        centerFieldGoalsMade = 0,
        leftSideFieldGoals = 0,
        leftSideFieldGoalsMade = 0,
        rightSideFieldGoals = 0,
        rightSideFieldGoalsMade = 0
    )
)