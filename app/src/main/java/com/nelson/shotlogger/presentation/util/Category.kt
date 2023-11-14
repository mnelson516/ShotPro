package com.nelson.shotlogger.presentation.util

import com.nelson.shotlogger.presentation.model.Exercise

data class Category(
    var date: String,
    val items: List<Exercise>
)
