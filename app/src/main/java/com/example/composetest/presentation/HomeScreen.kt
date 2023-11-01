package com.example.composetest.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.insights.InsightsViewModel
import com.example.composetest.presentation.insights.SemicircleView
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NeonOrange
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
                LogWorkoutsCard(navController)
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
fun LogWorkoutsCard(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(NeonOrange)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("add_workout")
            }
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.log_workout),
                style = Typography.h4,
                fontSize = 18.sp
            )
            Text(
                text = stringResource(id = R.string.log_own_workout),
                style = Typography.h5,
                color = Color.White,
                fontSize = 12.sp
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.forward_arrow),
                contentDescription = "Go to add workout screen",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

