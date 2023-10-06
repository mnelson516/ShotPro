package com.example.composetest.presentation.model

data class Exercise (
    val date: String,
    val name: String,
    val shotsMade: Int,
    val totalShots: Int,
    val range: String,
    val location: String,
)