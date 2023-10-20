package com.example.composetest.presentation.model

import java.time.LocalDate
import java.time.LocalDateTime

data class Exercise (
    val date: LocalDateTime,
    val name: String,
    val side: String,
    val shotsMade: Int,
    val totalShots: Int,
    val range: String,
    val location: String,
)