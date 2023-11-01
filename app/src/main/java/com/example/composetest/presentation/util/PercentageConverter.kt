package com.example.composetest.presentation.util

import kotlin.math.roundToInt

object PercentageConverter {
    fun convertToPercentage(shotsMade: Int, shotsTaken: Int): String {
        return if (shotsTaken > 0 && shotsTaken >= shotsMade) ((shotsMade.toDouble() / shotsTaken.toDouble()) * 100).roundToInt().toString()
        else "INVALID INPUT"
    }
}