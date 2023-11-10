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
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.domain.OrderType
import com.example.composetest.presentation.history.HistoryEvent
import com.example.composetest.presentation.history.HistoryViewModel
import com.example.composetest.presentation.model.FieldGoalData
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsScreen(viewModel: InsightsViewModel, historyViewModel: HistoryViewModel, navController: NavController) {
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
                    InsightsContent(data, navController, historyViewModel)
                } ?: run {
                    EmptyScreenState()
                }
            }
        }
    }

}

@Composable
fun InsightsContent(data: FieldGoalData, navController: NavController, historyViewModel: HistoryViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(top = 24.dp))
        SemicircleView(text = stringResource(id = R.string.total_field_goals),
            totalShots = data.totalFieldGoals,
            shotsMade = data.totalFieldGoalsMade,
            isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.Default(OrderType.Default),
                        ""
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.right_side_field_goals),
            totalShots = data.rightSideFieldGoals, shotsMade = data.rightSideFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.Right(OrderType.Side),
                        "Right"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.left_side_field_goals),
            totalShots = data.leftSideFieldGoals, shotsMade = data.leftSideFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.Left(OrderType.Side),
                        "Left"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.baseline_field_goals),
            totalShots = data.baseLineFieldGoals, shotsMade = data.baseLineFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.Baseline(OrderType.Location),
                        "Right"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.center_field_goals),
            totalShots = data.centerFieldGoals, shotsMade = data.centerFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.Center(OrderType.Location),
                        "Center"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.diagonal_field_goals),
            totalShots = data.diagonalFieldGoals, shotsMade = data.diagonalFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.Diagonal(OrderType.Location),
                        "Diagonal"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.elbow_field_goals),
            totalShots = data.elbowFieldGoals, shotsMade = data.elbowFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.Elbow(OrderType.Location),
                        "Elbow"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.close_range_field_goals),
            totalShots = data.closeRangeFieldGoals, shotsMade = data.closeRangeFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.CloseRange(OrderType.Range),
                        "Close Range"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.mid_range_field_goals),
            totalShots = data.midRangeFieldGoals, shotsMade = data.midRangeFieldGoalsMade, isThreePointer = false,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.MidRange(OrderType.Range),
                        "Mid Range"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 12.dp))
        SemicircleView(text = stringResource(id = R.string.three_point_field_goals),
            totalShots = data.threePointFieldGoals, shotsMade = data.threePointFieldGoalsMade, isThreePointer = true,
            onClick = {
                historyViewModel.onEvent(
                    HistoryEvent.GetExercises(
                        ExerciseOrder.ThreePointRange(OrderType.Range),
                        "Three Pointer"
                    )
                )
                navController.navigate("Graph Screen")
            })
        Spacer(modifier = Modifier.padding(top = 90.dp))
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

