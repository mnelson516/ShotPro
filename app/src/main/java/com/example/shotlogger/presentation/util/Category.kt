package com.example.shotlogger.presentation.util

import com.example.shotlogger.presentation.model.Exercise

data class Category(
    var date: String,
    val items: List<Exercise>
)
