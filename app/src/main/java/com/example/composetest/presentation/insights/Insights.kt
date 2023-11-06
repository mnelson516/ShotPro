package com.example.composetest.presentation.insights

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composetest.R
import com.example.composetest.presentation.model.FieldGoalData
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreen(viewModel: InsightsViewModel) {
    viewModel.getFieldGoals()
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.insights),
                        style = Typography.h1
                    )
                },
                colors = TopAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue,
                    actionIconContentColor = NavyBlue,
                    navigationIconContentColor = NavyBlue,
                    titleContentColor = NavyBlue
                )
            )
        }
    ) {
        Box(modifier = Modifier
            .background(WhiteBackground)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                state.value.data?.let { data ->
                    InsightsContent(data)
                } ?: run {
                    EmptyScreenState()
                }
            }
        }
    }

}

@Composable
fun InsightsContent(data: FieldGoalData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp, bottom = 110.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(top = 24.dp))
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.total_field_goals),
            totalShots = data.totalFieldGoals, shotsMade = data.totalFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.right_side_field_goals),
            totalShots = data.rightSideFieldGoals, shotsMade = data.rightSideFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.left_side_field_goals),
            totalShots = data.leftSideFieldGoals, shotsMade = data.leftSideFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.baseline_field_goals),
            totalShots = data.baseLineFieldGoals, shotsMade = data.baseLineFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.center_field_goals),
            totalShots = data.centerFieldGoals, shotsMade = data.centerFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.diagonal_field_goals),
            totalShots = data.diagonalFieldGoals, shotsMade = data.diagonalFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.elbow_field_goals),
            totalShots = data.elbowFieldGoals, shotsMade = data.elbowFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.close_range_field_goals),
            totalShots = data.closeRangeFieldGoals, shotsMade = data.closeRangeFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.mid_range_field_goals),
            totalShots = data.midRangeFieldGoals, shotsMade = data.midRangeFieldGoalsMade, isThreePointer = false)
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.three_point_field_goals),
            totalShots = data.threePointFieldGoals, shotsMade = data.threePointFieldGoalsMade, isThreePointer = true)
    }
}
@Composable
fun EmptyScreenState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.no_content_yet))
    }
}

