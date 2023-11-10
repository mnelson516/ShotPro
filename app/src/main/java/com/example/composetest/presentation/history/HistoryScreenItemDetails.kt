package com.example.composetest.presentation.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.insights.DetailsArc
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground
import com.example.composetest.presentation.util.DateFormatter
import com.example.composetest.presentation.util.ImageSelector
import java.time.LocalDateTime

@Composable
fun HistoryScreenDetails(state: State<HistoryState>, navController: NavController) {
    val exercise = state.value.currentDetails
    exercise?.let {
        DetailsScreenContent(exercise, navController)
    } ?: run {
        EmptyDetailsScreenState()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContent(exercise: Exercise, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Exercise Details",
                        style = Typography.h1
                    )
                },
                colors = TopAppBarColors(
                    containerColor = NavyBlue,
                    scrolledContainerColor = NavyBlue,
                    actionIconContentColor = NavyBlue,
                    navigationIconContentColor = NavyBlue,
                    titleContentColor = NavyBlue
                ),
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back Arrow",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                navController.navigateUp()
                            }
                    )
                }
            )
        }
    ) {
        Box(modifier = Modifier
            .background(WhiteBackground)
            .padding(it)
            .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = exercise.name,
                    style = Typography.h1,
                    fontSize = 28.sp,
                    textDecoration = TextDecoration.Underline,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                )
                Text(
                    text = (DateFormatter.formatDateWithYear(exercise.date)),
                    style = Typography.h2,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(top = 4.dp)
                )
                Image(
                    painter = painterResource(id = ImageSelector.getImage(exercise)),
                    contentDescription = "Shot Location",
                    modifier = Modifier
                        .size(300.dp)
                )
                DetailsArc(
                    shotsMade = exercise.shotsMade,
                    totalShots = exercise.totalShots,
                    isThreePointer = exercise.range == "Three Pointer"
                )
            }
        }
    }
}

@Composable
fun EmptyDetailsScreenState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.no_content_yet))
    }
}