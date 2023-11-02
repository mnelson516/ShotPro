package com.example.composetest.presentation.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.insights.InsightsViewModel
import com.example.composetest.presentation.insights.SemicircleView
import com.example.composetest.presentation.insights.getBrushColor
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.Typography

@Composable
fun HomeScreen(navController: NavController, insightsViewModel: InsightsViewModel) {
    val state = insightsViewModel.state.collectAsState()
    insightsViewModel.getFieldGoals()
    Scaffold {
        Box(modifier = Modifier
            .background(NavyBlue)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.height(48.dp))
                TitleSection(title = stringResource(id = R.string.welcome_back))
                state.value.data?.let { data ->
                    Box(
                        modifier = Modifier.padding(horizontal = 14.dp)
                    ) {
                        SemicircleView(text = stringResource(id = R.string.total_field_goals),
                            totalShots = data.totalFieldGoals, shotsMade = data.totalFieldGoalsMade, isThreePointer = false)
                    }
                } ?: run {
                    Box(
                        modifier = Modifier.padding(horizontal = 14.dp)
                    ) {
                        SemicircleView(text = stringResource(id = R.string.no_data_yet),
                            totalShots = 1, shotsMade = 0, isThreePointer = false)
                    }
                }
            }
        }
    }
}

@Composable
fun TitleSection(
    title: String
) {
    Text(
        text = title,
        style = Typography.h5,
        fontSize = 24.sp,
        modifier = Modifier.padding(start = 20.dp)
    )
}

@Composable
fun FieldGoalGauge(percentage: Float, isThreePointer: Boolean) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(175.dp)
                .clipToBounds()
                .padding(14.dp)
        ) {
            drawArc(
                brush = SolidColor(Color.LightGray),
                size = Size(size.width, size.height),
                startAngle = 210f,
                sweepAngle = 240f,
                topLeft = Offset(0f, size.width.times(0.25f)),
                useCenter = false,
                style = Stroke(35f, cap = StrokeCap.Round)
            )
            drawArc(
                brush = getBrushColor(percentage, isThreePointer),
                size = Size(size.width, size.height),
                startAngle = 210f,
                sweepAngle = 240 * (percentage / 100),
                topLeft = Offset(0f, size.width.times(0.25f)),
                useCenter = false,
                style = Stroke(35f, cap = StrokeCap.Round)
            )
        }
        Row {
            Text(
                text = percentage.toInt().toString(),
                style = Typography.h4,
                color = Color.Black,
                fontSize = 26.sp,
                modifier = Modifier.offset(x = 0.dp, y = (12).dp)
            )
            Text(
                text = "%",
                style = Typography.h4,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.offset(x = 0.dp, y = (12).dp)
            )
        }
    }
}


