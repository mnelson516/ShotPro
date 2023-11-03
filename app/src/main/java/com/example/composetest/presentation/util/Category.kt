package com.example.composetest.presentation.util

import com.example.composetest.presentation.model.Exercise

data class Category(
    var date: String,
    val items: List<Exercise>
)
