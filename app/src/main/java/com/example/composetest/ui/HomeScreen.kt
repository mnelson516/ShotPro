package com.example.composetest.ui

import androidx.compose.foundation.background
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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.R
import com.example.composetest.ui.theme.NavyBlue
import com.example.composetest.ui.theme.NeonOrange
import com.example.composetest.ui.theme.Typography
import com.example.composetest.util.QuoteGenerator

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
                FeaturedWorkoutsCard()
                FeatureText()
            }
        }
    }
}

@Composable
fun Greeting() {
    var quoteGenerator = QuoteGenerator()
    var quotePair = quoteGenerator.generateRandomQuote()
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
                text = "Welcome Back!",
                style = Typography.h5,
                fontSize = 16.sp
            )
//            Text(
//                text = quotePair.first,
//                fontSize = 16.sp,
//                style = Typography.h6,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .padding(top = 6.dp)
//            )
//            Text(
//                text = "- " + quotePair.second,
//                fontSize = 16.sp,
//                style = Typography.h6,
//                textAlign = TextAlign.Center,
//
//                modifier = Modifier
//                    .padding(top = 4.dp)
//            )
        }
    }
}

@Composable
fun FeaturedWorkoutsCard() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(NeonOrange)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Log Workout",
                style = Typography.h4,
                fontSize = 18.sp
            )
            Text(
                text = "Log your own workout from scratch",
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
                contentDescription = "Go to Featured Workouts",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun FeatureText() {
    Text(
        text = "Feature Workouts",
        style = Typography.h5,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(start = 20.dp)
    )
    Text(
        text = "Sample workouts from some of your favorite pros",
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

