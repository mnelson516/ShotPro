package com.nelson.shotlogger.presentation.util

import com.nelson.shotlogger.presentation.model.Exercise
import com.nelson.shotlogger.presentation.model.FieldGoalData
import kotlin.math.roundToInt

object PercentageConverter {
    fun convertToPercentage(shotsMade: Int, shotsTaken: Int): String {
        return if (shotsTaken > 0 && shotsTaken >= shotsMade) ((shotsMade.toDouble() / shotsTaken.toDouble()) * 100).roundToInt().toString()
        else "0"
    }
    fun getBestPercentage(data: FieldGoalData?): Pair<String, String> {
        if (data != null) {
            var currMaxPair = Pair("Right Side", convertToPercentage(data.rightSideFieldGoalsMade, data.rightSideFieldGoals))
            if (convertToPercentage(data.leftSideFieldGoalsMade, data.leftSideFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Left Side", convertToPercentage(data.leftSideFieldGoalsMade, data.leftSideFieldGoals))
            }
            if (convertToPercentage(data.closeRangeFieldGoalsMade, data.closeRangeFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Close Range", convertToPercentage(data.closeRangeFieldGoalsMade, data.closeRangeFieldGoals))
            }
            if (convertToPercentage(data.midRangeFieldGoalsMade, data.midRangeFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Mid Range", convertToPercentage(data.midRangeFieldGoalsMade, data.midRangeFieldGoals))
            }
            if (convertToPercentage(data.threePointFieldGoalsMade, data.threePointFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Three Pointer", convertToPercentage(data.threePointFieldGoalsMade, data.threePointFieldGoals))
            }
            if (convertToPercentage(data.baseLineFieldGoalsMade, data.baseLineFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Baseline", convertToPercentage(data.baseLineFieldGoalsMade, data.baseLineFieldGoals))
            }
            if (convertToPercentage(data.diagonalFieldGoalsMade, data.diagonalFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Diagonal", convertToPercentage(data.diagonalFieldGoalsMade, data.diagonalFieldGoals))
            }
            if (convertToPercentage(data.elbowFieldGoalsMade, data.elbowFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Elbow", convertToPercentage(data.elbowFieldGoalsMade, data.elbowFieldGoals))
            }
            if (convertToPercentage(data.centerFieldGoalsMade, data.centerFieldGoals).toInt() > currMaxPair.second.toInt()) {
                currMaxPair = Pair("Center", convertToPercentage(data.centerFieldGoalsMade, data.centerFieldGoals))
            }

            return currMaxPair
        } else {
            return Pair("None", "0")
        }
    }

    fun getWorstPercentage(data: FieldGoalData?): Pair<String, String> {
        if (data != null) {
            var currMaxPair = Pair(
                "Right Side",
                convertToPercentage(data.rightSideFieldGoalsMade, data.rightSideFieldGoals)
            )
            if (convertToPercentage(
                    data.leftSideFieldGoalsMade,
                    data.leftSideFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Left Side",
                    convertToPercentage(data.leftSideFieldGoalsMade, data.leftSideFieldGoals)
                )
            }
            if (convertToPercentage(
                    data.closeRangeFieldGoalsMade,
                    data.closeRangeFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Close Range",
                    convertToPercentage(data.closeRangeFieldGoalsMade, data.closeRangeFieldGoals)
                )
            }
            if (convertToPercentage(
                    data.midRangeFieldGoalsMade,
                    data.midRangeFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Mid Range",
                    convertToPercentage(data.midRangeFieldGoalsMade, data.midRangeFieldGoals)
                )
            }
            if (convertToPercentage(
                    data.threePointFieldGoalsMade,
                    data.threePointFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Three Pointer",
                    convertToPercentage(data.threePointFieldGoalsMade, data.threePointFieldGoals)
                )
            }
            if (convertToPercentage(
                    data.baseLineFieldGoalsMade,
                    data.baseLineFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Baseline",
                    convertToPercentage(data.baseLineFieldGoalsMade, data.baseLineFieldGoals)
                )
            }
            if (convertToPercentage(
                    data.diagonalFieldGoalsMade,
                    data.diagonalFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Diagonal",
                    convertToPercentage(data.diagonalFieldGoalsMade, data.diagonalFieldGoals)
                )
            }
            if (convertToPercentage(
                    data.elbowFieldGoalsMade,
                    data.elbowFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Elbow",
                    convertToPercentage(data.elbowFieldGoalsMade, data.elbowFieldGoals)
                )
            }
            if (convertToPercentage(
                    data.centerFieldGoalsMade,
                    data.centerFieldGoals
                ).toInt() < currMaxPair.second.toInt()
            ) {
                currMaxPair = Pair(
                    "Center",
                    convertToPercentage(data.centerFieldGoalsMade, data.centerFieldGoals)
                )
            }

            return currMaxPair
        } else {
            return Pair("None", "0")
        }
    }

    fun getPointAverage(list: List<Exercise>): Float {
        val averageList = mutableListOf<Float>()

        for (exercise in list) {
            averageList.add(exercise.shotsMade.toFloat() / exercise.totalShots.toFloat())
        }

        return (averageList.average() * 100).toFloat()
    }
}