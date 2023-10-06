package com.example.composetest.presentation

import android.content.Intent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.R
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.Typography

@Composable
@Preview
fun HomeScreen() {
    Scaffold {
        Box(modifier = Modifier
            .background(NavyBlue)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                Greeting()
                LogWorkoutsCard()
                FeatureText()
            }
        }
    }
}

@Composable
fun Greeting() {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = Typography.h5,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun LogWorkoutsCard() {
    val context = LocalContext.current
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
                context.startActivity(
                    Intent(context, AddWorkoutActivity::class.java)
                )
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

@Composable
fun FeatureText() {
    Text(
        text = stringResource(id = R.string.feature_workouts),
        style = Typography.h5,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(start = 20.dp)
    )
    Text(
        text = stringResource(id = R.string.feature_workout_desc),
        style = Typography.h5,
        color = Color.White,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(start = 20.dp)
    )
}


@Composable
fun CardContent() {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = NeonOrange)
        ) {

        }
        Box() {

        }
    }
}

