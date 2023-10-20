package com.example.composetest.presentation.AddExercise

import android.app.Activity
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.SecondaryBlue
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.util.InputValidator
import java.time.LocalDateTime

@Composable
fun WorkoutScreen(state: AddExercisesState, onEvent: (AddExerciseEvent) -> Unit, navController: NavController) {
    val scaffoldState = rememberScaffoldState()


    if (state.showPopup) {
        ExerciseDialog(
            updateExercise = { exercise ->
                onEvent(AddExerciseEvent.AddExercise(exercise))
            }, showPopup = {
                onEvent( AddExerciseEvent.ShowPopup(false))

            }, onEvent = onEvent
        )
    }

    Scaffold(
        floatingActionButton = {
            Row(
                horizontalArrangement = Arrangement.End
            ) {
                if (state.exercises.isNotEmpty()) SaveButton(navController = navController, onEvent)
                FloatingActionButton(
                    shape = CircleShape,
                    backgroundColor = NeonOrange,
                    onClick = {
                        onEvent(AddExerciseEvent.ShowPopup(true))
                    },
                    contentColor = Color.White,
                    modifier = Modifier
                        .padding(start = 14.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
                }

            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .background(NavyBlue)
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            TopBar(navController)
            if (state.exercises.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.no_exercises_added),
                    style = Typography.h6,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 24.dp))
            } else {
                ExerciseList(exercises = state.exercises, onEvent)
            }
        }
    }
}

@Composable
fun TopBar(navController: NavController) {
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
                    navController.navigateUp()
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
fun ExerciseDialog(
    updateExercise: (Exercise) -> Unit,
    showPopup: (Boolean) -> Unit,
    onEvent: (AddExerciseEvent) -> Unit
) {
    val spinnerRangeList = listOf("Close Range", "Mid Range", "Three Pointer")
    val spinnerLocationList = listOf("Center", "Baseline", "Diagonal")
    var exerciseName by remember { mutableStateOf("") }
    var shotsMade by remember { mutableStateOf("") }
    var totalShots by remember { mutableStateOf("") }
    var isShotNumError by remember { mutableStateOf(false) }
    var isFieldNotFilledError by remember { mutableStateOf(false) }
    var locationSpinnerString by remember { mutableStateOf(spinnerLocationList.first()) }
    var rangeSpinnerString by remember { mutableStateOf(spinnerRangeList.first()) }


    Dialog(
        onDismissRequest = {
            onEvent(AddExerciseEvent.ShowPopup(false))
        }) {
        Card(
            modifier = Modifier
                .fillMaxSize()
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
                            onEvent(AddExerciseEvent.ShowPopup(false))
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
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth(),
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
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth(),
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
                        .padding(bottom = 12.dp)
                        .fillMaxWidth(),
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
                        when(
                            InputValidator.isValidExerciseInput(
                                exerciseName,
                                shotsMade,
                                totalShots
                            )) {
                            InputValidator.EMPTY_FIELD -> isFieldNotFilledError = true
                            InputValidator.INVALID_INPUT -> isShotNumError = true
                            InputValidator.VALID_INPUT ->  {
                                updateExercise(
                                    Exercise(
                                        date = LocalDateTime.now(),
                                        name = exerciseName,
                                        side = "right",
                                        shotsMade = shotsMade.toInt(),
                                        totalShots = totalShots.toInt(),
                                        location = locationSpinnerString,
                                        range = rangeSpinnerString
                                    )
                                )
                                showPopup(false)
                            }
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
                            .padding(bottom = 12.dp, top = 12.dp)
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
fun ExerciseList(exercises: List<Exercise>, onEvent: (AddExerciseEvent) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(exercises) {exercise ->
            ExerciseCard(exercise = exercise, onEvent = onEvent)
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise, onEvent: (AddExerciseEvent) -> Unit) {
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
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Delete Button",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable {
                        onEvent(AddExerciseEvent.DeleteExercise(exercise))
                    })
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Shots: " + exercise.shotsMade.toString() + "/" + exercise.totalShots.toString(),
                style = Typography.h5,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp
            )

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
fun SaveButton(navController: NavController, onEvent: (AddExerciseEvent) -> Unit) {
    val context = LocalContext.current

    ExtendedFloatingActionButton(
        onClick = {
            onEvent(AddExerciseEvent.SaveExercises)
            Toast.makeText(context, "Successfully Saved Exercises", Toast.LENGTH_LONG).show()
            navController.navigateUp()
        },
        icon = { Icon(Icons.Filled.Edit, stringResource(id = R.string.save_workout), tint = Color.White) },
        text = { Text(text = stringResource(id = R.string.save_workout), color = Color.White) },
        modifier = Modifier,
        containerColor = NeonOrange,
        contentColor = Color.White
    )
}