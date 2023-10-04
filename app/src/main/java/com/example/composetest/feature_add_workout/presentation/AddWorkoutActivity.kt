package com.example.composetest.feature_add_workout.presentation

import android.app.Activity
import android.os.Bundle
import android.transition.Slide
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.composetest.theme.NavyBlue
import com.example.composetest.theme.NeonOrange
import com.example.composetest.theme.SecondaryBlue
import com.example.composetest.theme.Typography
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.flow.MutableStateFlow

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

    @Composable
    @Preview
    fun WorkoutScreen() {
        var showPopup by remember { mutableStateOf(false) }
        val _exerciseList = remember { MutableStateFlow(listOf<Exercise>()) }
        val exerciseList by remember { _exerciseList }.collectAsState()

        if (showPopup) {

            val spinnerRangeList = listOf("Close Range", "Mid Range", "Three Pointer")
            val spinnerLocationList = listOf("Center", "Baseline", "Diagonal")
            var exerciseName by remember { mutableStateOf("") }
            var shotsMade by remember { mutableStateOf("") }
            var totalShots by remember { mutableStateOf("") }
            var isShotNumError by remember { mutableStateOf(false)}
            var isFieldNotFilledError by remember { mutableStateOf(false)}
            var locationSpinnerString by remember { mutableStateOf(spinnerLocationList.first()) }
            var rangeSpinnerString by remember { mutableStateOf(spinnerRangeList.first()) }


            Dialog(
                onDismissRequest = {
                    showPopup = false
                }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 16.dp)
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_close),
                            contentDescription = "Close Popup",
                            modifier = Modifier
                                .align(Alignment.End)
                                .clickable {
                                    showPopup = false
                                }
                        )

                        Text(
                            text = stringResource(id = R.string.add_exercise),
                            fontSize = 20.sp,
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
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .padding(bottom = 12.dp),
                            onValueChange = {
                                totalShots = it
                                if (totalShots.isNotEmpty()) {
                                    isShotNumError = shotsMade.toInt() > totalShots.toInt()
                                }
                            },
                            trailingIcon = {
                                if (isShotNumError)
                                    Icon(
                                        painterResource(id = R.drawable.ic_error),
                                        contentDescription = "Error Icon",
                                        tint = MaterialTheme.colors.error)
                            },
                            isError = isShotNumError,
                            label = {
                                Text("Total Shots")
                            }
                        )
                        if (isShotNumError) {
                            Text(
                                text = "Cannot have more made shots than total shots",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier
                                    .padding(bottom = 12.dp),
                            )
                        }

                        Text(
                            "Angle",
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        LocationDropDown(
                            list = spinnerLocationList,
                            preselected = spinnerLocationList.first(),
                            onSelectionChanged = {
                               locationSpinnerString = it
                            },
                            modifier = Modifier
                                .padding(bottom = 12.dp))

                        Text(
                            "Distance",
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        LocationDropDown(
                            list = spinnerRangeList,
                            preselected = spinnerRangeList.first(),
                            onSelectionChanged = {
                                rangeSpinnerString = it
                            },
                            modifier = Modifier
                                .padding(bottom = 16.dp))

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(48.dp),
                            onClick = {
                                if (exerciseName.isEmpty() || shotsMade.isEmpty() || totalShots.isEmpty()) {
                                    isFieldNotFilledError = true
                                }
                                else if (shotsMade.toInt() > totalShots.toInt()) {
                                    isShotNumError = true
                                } else {
                                        val newList = ArrayList(exerciseList)
                                        newList.add(
                                            Exercise(
                                                name = exerciseName,
                                                shotsMade = shotsMade.toInt(),
                                                totalShots = totalShots.toInt(),
                                                location = locationSpinnerString,
                                                range = rangeSpinnerString
                                            )
                                        )
                                        _exerciseList.value = newList
                                        showPopup = false
                                }
                            },
                            content =  {
                                Text(
                                    "Add Exercise"
                                )
                            }
                        )

                        if (isFieldNotFilledError) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(bottom = 12.dp)
                            ) {
                                Icon (
                                    painterResource(id = R.drawable.ic_error),
                                    contentDescription = "Error Icon",
                                    tint = MaterialTheme.colors.error
                                )
                                Text(
                                    text = "Must Fill Out all Fields",
                                    color = MaterialTheme.colors.error,
                                    style = MaterialTheme.typography.caption,
                                )
                            }
                        }
                    }
                }
            }
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
            Column(
                modifier = Modifier
                    .background(NavyBlue)
                    .padding(it)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                TopBar()
                if (exerciseList.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.no_exercises_added),
                        style = Typography.h6,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 24.dp))
                } else {
                    ExerciseList(exercises = exerciseList)
                    SaveButton(exercises = exerciseList)
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
    fun LocationDropDown(
        list: List<String>,
        preselected: String,
        onSelectionChanged: (myData: String) -> Unit,
        modifier: Modifier = Modifier
    ) {

        var selected by remember { mutableStateOf(preselected) }
        var expanded by remember { mutableStateOf(false) } // initial value

        OutlinedCard(
            modifier = modifier.clickable {
                expanded = !expanded
            }
        ) {


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = selected,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Icon(Icons.Outlined.ArrowDropDown, null, modifier = Modifier.padding(8.dp))

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.wrapContentWidth()
                ) {
                    list.forEach { listEntry ->

                        DropdownMenuItem(
                            onClick = {
                                selected = listEntry
                                expanded = false
                                onSelectionChanged(selected)
                            },
                            text = {
                                Text(
                                    text = listEntry,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .align(Alignment.Start)
                                )
                            },
                        )
                    }
                }

            }
        }
    }

    @Composable
    fun ExerciseList(exercises: List<Exercise>) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(exercises) {exercise ->
                ExerciseCard(exercise = exercise)
            }
        }
    }

    @Composable
    fun ExerciseCard(exercise: Exercise) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(SecondaryBlue)
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = exercise.name,
                    style = Typography.h5,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp)
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Edit Button",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 8.dp))
            }

            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = exercise.shotsMade.toString(),
                    style = Typography.h5,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 14.sp)
                Text(
                    text = "/" + exercise.totalShots.toString(),
                    style = Typography.h5,
                    fontSize = 14.sp)
            }

            Row(
                modifier = Modifier
                    .padding(top = 6.dp)
            ) {
                Text(
                    text = "Range: ",
                    style = Typography.h5,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 14.sp)

                Text(
                    text = exercise.range,
                    style = Typography.h5,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 6.dp)
            ) {
                Text(
                    text = "Angle: ",
                    style = Typography.h5,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 14.sp
                )
                Text(
                    text = exercise.location,
                    style = Typography.h5,
                    fontSize = 14.sp
                )
            }
        }
    }

    @Composable
    fun SaveButton(exercises: List<Exercise>) {
        ExtendedFloatingActionButton(
            onClick = {

            },
            icon = { Icon(Icons.Filled.Edit, stringResource(id = R.string.save_workout)) },
            text = { Text(text = stringResource(id = R.string.save_workout)) },
        )
    }
}

