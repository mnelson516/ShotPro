package com.example.composetest.presentation.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.insights.FieldGoalGauge
import com.example.composetest.presentation.insights.InsightsViewModel
import com.example.composetest.presentation.insights.SemicircleView
import com.example.composetest.presentation.insights.getBrushColor
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground
import com.example.composetest.presentation.util.PercentageConverter

@Composable
fun HomeScreen(navController: NavController, insightsViewModel: InsightsViewModel) {
    val state = insightsViewModel.state.collectAsState()
    insightsViewModel.getFieldGoals()
    Scaffold {
        Column(modifier = Modifier
            .background(NavyBlue)
            .padding(it)
            .fillMaxSize()
        ) {
            state.value.data?.let { data ->
                GaugeSection(
                    percentage = PercentageConverter.convertToPercentage(data.totalFieldGoalsMade, data.totalFieldGoals).toFloat(),
                    text = stringResource(id = R.string.field_goals)
                )
                CardSection()
            } ?: run {
                GaugeSection(
                    percentage = PercentageConverter.convertToPercentage(0, 1).toFloat(),
                    text = stringResource(id = R.string.no_data_available)
                )
                CardSection()
            }
        }
    }
}

@Composable
fun GaugeSection(percentage: Float, text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(height = 325.dp)
            .background(NavyBlue)
            .fillMaxWidth()
    ) {
        Column {
            Spacer(modifier = Modifier.height(28.dp))
            FieldGoalGauge(percentage = percentage, text = text)
        }
    }
}

@Composable
fun CardSection() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(WhiteBackground)
            .padding(bottom = 80.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            HomeScreenCard(
                title = "Total Shots",
                info = "20",
                modifier = Modifier
                    .padding(6.dp)
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, NavyBlue, RoundedCornerShape(12.dp))
            )
            HomeScreenCard(
                title = "Total Shots",
                info = "20",
                modifier = Modifier
                    .padding(6.dp)
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, NavyBlue, RoundedCornerShape(12.dp))
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            HomeScreenCard(
                title = "Total Shots",
                info = "20",
                modifier = Modifier
                    .padding(6.dp)
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, NavyBlue, RoundedCornerShape(12.dp))
            )
            HomeScreenCard(
                title = "Total Shots",
                info = "20",
                modifier = Modifier
                    .padding(6.dp)
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, NavyBlue, RoundedCornerShape(12.dp))
            )
        }
    }
}

@Composable
fun HomeScreenCard(title: String, info: String, modifier: Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = Typography.h2,
                fontSize = 14.sp,
                color = Color.Black
            )
            Text(
                text = title,
                style = Typography.h3,
                fontSize = 20.sp,
                color = Color.Black
            )
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

@Preview
@Composable
fun HomePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clip(RectangleShape)
            .background(NavyBlue)
    ) {
        GaugeSection(
            percentage = PercentageConverter.convertToPercentage(4, 20).toFloat(),
            text = stringResource(id = R.string.field_goals)
        )
        CardSection()
    }
}


