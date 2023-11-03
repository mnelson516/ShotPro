package com.example.composetest.presentation.home

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.R
import com.example.composetest.presentation.insights.FieldGoalGauge
import com.example.composetest.presentation.insights.InsightsViewModel
import com.example.composetest.presentation.model.FieldGoalData
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NavyGradient
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground
import com.example.composetest.presentation.util.PercentageConverter

@Composable
fun HomeScreen(insightsViewModel: InsightsViewModel) {
    val state = insightsViewModel.state.collectAsState()
    insightsViewModel.getFieldGoals()
    Scaffold {
        Column(
            modifier = Modifier
                .background(NavyGradient)
                .padding(it)
                .fillMaxSize()
        ) {
            state.value.data?.let { data ->
                GaugeSection(
                    percentage = PercentageConverter.convertToPercentage(data.totalFieldGoalsMade, data.totalFieldGoals).toFloat(),
                    text = stringResource(id = R.string.field_goals)
                )
                CardSection(data)
            } ?: run {
                GaugeSection(
                    percentage = PercentageConverter.convertToPercentage(0, 1).toFloat(),
                    text = stringResource(id = R.string.no_data_available)
                )
                CardSection(null)
            }
        }
    }
}

@Composable
fun GaugeSection(percentage: Float, text: String) {
    val colorStops = arrayOf(
        0.0f to NavyBlue,
        1f to NavyGradient
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(height = 325.dp)
            .background(Brush.verticalGradient(colorStops = colorStops))
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = Typography.h1,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 24.dp, top = 12.dp)
                    .background(Color.Transparent)
            )
            Spacer(modifier = Modifier.height(28.dp))
            FieldGoalGauge(percentage = percentage, text = text)
        }
    }
}

@Composable
fun CardSection(data: FieldGoalData?) {
    val totalShots = data?.totalFieldGoals?.toString() ?: "0"
    val totalShotsMade = data?.totalFieldGoalsMade?.toString() ?: "0"
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(WhiteBackground)
            .padding(bottom = 90.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.your_breakdown),
            style = Typography.h1,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f)
        ) {
            HomeScreenCard(
                title = "Total Shots:",
                info = totalShots,
                modifier = Modifier
                    .padding(6.dp)
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, NavyBlue, RoundedCornerShape(12.dp))
            )
            HomeScreenCard(
                title = "Made Shots:",
                info = totalShotsMade,
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
                title = "Best Location: \n" + PercentageConverter.getBestPercentage(data).first,
                info = PercentageConverter.getBestPercentage(data).second + "%",
                modifier = Modifier
                    .padding(6.dp)
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, NavyBlue, RoundedCornerShape(12.dp))
            )
            HomeScreenCard(
                title = "Worst Location: \n" + PercentageConverter.getWorstPercentage(data).first,
                info = PercentageConverter.getWorstPercentage(data).second + "%",
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
fun HomeScreenCard(title: String?, info: String?, modifier: Modifier) {
    val titleString = title ?: "No Data Yet"
    val infoString = info ?: "0"
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = titleString,
                style = Typography.h2,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Text(
                text = infoString,
                style = Typography.h1,
                fontSize = 32.sp,
                color = NeonOrange,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


