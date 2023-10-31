package com.example.composetest.presentation.addworkout

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.SecondaryBlue
import com.example.composetest.presentation.theme.Typography


@Composable
fun WorkoutScreen(state: AddExercisesState,
                  onEvent: (AddExerciseEvent) -> Unit,
                  navController: NavController)
{
    val scaffoldState = rememberScaffoldState()
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
                        navController.navigate("add_exercise")
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