package com.example.composetest.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composetest.R
import com.example.composetest.model.Exercise
import com.example.composetest.ui.theme.NavyBlue
import com.example.composetest.ui.theme.NeonOrange
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

    @OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
    @Composable
    @Preview
    fun WorkoutScreen() {
        var showPopup by remember { mutableStateOf(false) }

        if (showPopup) {
            InputDialog()
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    backgroundColor = NeonOrange,
                    onClick = {
                        showPopup = true
                    },
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
                }
            }
        ) {
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
    fun InputDialog() {
        var exerciseName by remember { mutableStateOf("") }
        var shotsMade by remember { mutableStateOf("") }
        var totalShots by remember { mutableStateOf("") }
        var shotLocation by remember { mutableStateOf("") }

        Dialog(
            onDismissRequest = {

        }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {


                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                ) {

                    Text(
                        text = stringResource(id = R.string.add_exercise),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    OutlinedTextField(
                        value = exerciseName,
                        maxLines = 1,
                        modifier = Modifier.padding(bottom = 12.dp),
                        onValueChange = {
                            exerciseName = it
                        },
                        label = {
                            Text("Exercise Name")
                        }
                    )

                    OutlinedTextField(
                        value = shotsMade,
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.padding(bottom = 12.dp),
                        onValueChange = {
                            shotsMade = it
                        },
                        label = {
                            Text("Shots Made")
                        }
                    )

                    OutlinedTextField(
                        value = totalShots,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(bottom = 12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            totalShots = it
                        },
                        label = {
                            Text("Total Shots")
                        }
                    )

                    OutlinedTextField(
                        value = shotLocation,
                        maxLines = 1,
                        modifier = Modifier.padding(bottom = 12.dp),
                        onValueChange = {
                            shotLocation = it
                        },
                        label = {
                            Text("Shot Location")
                        }
                    )
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

    @Composable
    fun ExerciseColumn() {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {

        }
    }

    @Composable
    fun ExerciseCard(exercise: Exercise) {
        Box() {

        }
    }
}

