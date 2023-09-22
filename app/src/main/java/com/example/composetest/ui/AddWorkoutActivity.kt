package com.example.composetest.ui

import android.app.Activity
import android.os.Bundle
import android.transition.Slide
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.R
import com.example.composetest.ui.theme.NavyBlue
import com.example.composetest.ui.theme.Typography
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class AddWorkoutActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            enterTransition = Slide()
            exitTransition = Slide()
        }

        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()

            systemUiController.setStatusBarColor(color = colorResource(id = R.color.navy_blue))

            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                WorkoutScreen()
            }
        }
    }
}

@Composable
@Preview
fun WorkoutScreen() {
    Scaffold {
        Box(modifier = Modifier
            .background(NavyBlue)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.height(24.dp))
                TopBar()
            }
        }
    }
}

@Composable
fun TopBar() {
    val activity = LocalContext.current as? Activity
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(20.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow),
            contentDescription = "Back Arrow",
            tint = Color.White,
            modifier = Modifier
                .clickable {
                    activity?.onBackPressed()
                }
        )
        Text(
            text = stringResource(id = R.string.add_workout),
            style = Typography.h5,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}