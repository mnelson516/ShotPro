package com.example.composetest.presentation.addworkout

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.settings.SettingsViewModel
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.SecondaryBlue
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground
import com.example.composetest.presentation.util.RandomTipGenerator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(
    state: AddExercisesState,
    onEvent: (AddExerciseEvent) -> Unit,
    navController: NavController,
    settingsViewModel: SettingsViewModel
)
{
    val scaffoldState = rememberScaffoldState()
    val settingsState = settingsViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    androidx.compose.material3.Text(
                        text = stringResource(id = R.string.new_workout),
                        style = Typography.h1
                    )
                },
                actions = {
                    if (state.showSaveButton) {
                        IconButton(onClick = {
                            onEvent(AddExerciseEvent.SaveExercises)
                            Toast.makeText(context, "Successfully Saved Exercises", Toast.LENGTH_LONG).show()
                            navController.navigateUp()
                        }) {
                            Icon(
                                Icons.Filled.Edit,
                                stringResource(id = R.string.save_workout),
                                tint = Color.White
                            )
                        }
                    }
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
                                onEvent(AddExerciseEvent.ClearExercises)
                                navController.navigateUp()
                            }
                    )
                }

            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                backgroundColor = NeonOrange,
                onClick = {
                    navController.navigate("Add Exercise")
                },
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add icon"
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .background(WhiteBackground)
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            if (settingsState.value.tipsEnabled && state.showTipDialog) {
                TipDialog(onEvent = onEvent)
            }

            if (state.exercises.isEmpty()) {
                onEvent(AddExerciseEvent.ShowSaveButton(false))
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.no_exercises_added),
                        style = Typography.h3,
                        color = Color.Black,
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 16.dp, top = 24.dp)
                        )
                    }

            } else {
                onEvent(AddExerciseEvent.ShowSaveButton(true))
                ExerciseList(exercises = state.exercises, onEvent)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipDialog(onEvent: (AddExerciseEvent) -> Unit) {
    AlertDialog(
        modifier = Modifier.padding(horizontal = 12.dp),
        onDismissRequest = {
           onEvent(AddExerciseEvent.ShowDialog(false))
        }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(WhiteBackground)
                .border(1.dp, NavyBlue, RoundedCornerShape(12.dp))
                .padding(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.tip),
                    style = Typography.h1,
                    color = Color.Black,
                    fontSize = 24.sp,
                )
                Icon(
                    imageVector = Icons.Filled.Close,
                    "Close Button",
                    modifier = Modifier
                        .clickable {
                            onEvent(AddExerciseEvent.ShowDialog(false))
                        })
            }
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = RandomTipGenerator(LocalContext.current).generateRandomTip(),
                    style = Typography.h2,
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left
                )
            }
            Text(
                text = stringResource(id = R.string.disable_tips),
                style = Typography.h4,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
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
                    }
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Shots: " + exercise.shotsMade.toString() + "/" + exercise.totalShots.toString(),
                style = Typography.h3,
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
                style = Typography.h3,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp)

            Text(
                text = exercise.range,
                style = Typography.h3,
                fontSize = 14.sp
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 6.dp)
        ) {
            Text(
                text = "Angle: ",
                style = Typography.h3,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp
            )
            Text(
                text = exercise.location,
                style = Typography.h3,
                fontSize = 14.sp
            )
        }
    }
}