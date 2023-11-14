package com.nelson.shotlogger.presentation.util

object InputValidator {

    const val EMPTY_FIELD = "EMPTY_FIELD"
    const val INVALID_INPUT = "INVALID_INPUT"
    const val VALID_INPUT = "VALID_INPUT"

    /**
     * Test Cases:
     * 1. Empty Fields,
     * 2. Shots Made > Shots Taken
     */
    fun isValidExerciseInput(
        exerciseName: String,
        shotsMade: String,
        shotsTaken: String,
    ) : String {
        if (shotsMade.isEmpty() || shotsTaken.isEmpty() || exerciseName.isEmpty()) return EMPTY_FIELD

        if (shotsMade.toInt() > shotsTaken.toInt()) return INVALID_INPUT


        return VALID_INPUT
    }
}