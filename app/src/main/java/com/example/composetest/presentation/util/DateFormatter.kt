package com.example.composetest.presentation.util

import java.time.LocalDateTime

object DateFormatter {
    fun formatDate(exerciseDate: LocalDateTime, currentDateTime: LocalDateTime): String {
        return if (exerciseDate.year != currentDateTime.year) {
            exerciseDate.dayOfMonth.toString() + " " + exerciseDate.month.toString() + " " + exerciseDate.year.toString()
        } else {
            exerciseDate.month.toString() + " " + exerciseDate.dayOfMonth
        }
    }
}