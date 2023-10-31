package com.example.composetest.presentation.insights

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController


@Composable
fun InsightsScreen(navController: NavController, viewModel: InsightsViewModel) {
    viewModel.getFieldGoals()
    val state = viewModel.state.collectAsState()
    Column {
        Text(text = state.value.data.totalFieldGoalsMade.toString())
        Text(text = state.value.data.totalFieldGoals.toString())
    }
}