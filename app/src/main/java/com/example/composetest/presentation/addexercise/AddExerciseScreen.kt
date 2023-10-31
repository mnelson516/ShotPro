package com.example.composetest.presentation.addexercise

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.util.InputValidator
import java.time.LocalDateTime

@Composable
fun AddExerciseScreen(
    updateExercise: (Exercise) -> Unit,
    navController: NavController
) {
    val spinnerRangeList = listOf("Close Range", "Mid Range", "Three Pointer")
    val spinnerLocationList = listOf("Center", "Baseline", "Diagonal", "Elbow")
    val spinnerSideList = listOf("Right", "Left")
    var exerciseName by remember { mutableStateOf("") }
    var shotsMade by remember { mutableStateOf("") }
    var totalShots by remember { mutableStateOf("") }
    var isShotNumError by remember { mutableStateOf(false) }
    var isFieldNotFilledError by remember { mutableStateOf(false) }
    var locationSpinnerString by remember { mutableStateOf(spinnerLocationList.first()) }
    var rangeSpinnerString by remember { mutableStateOf(spinnerRangeList.first()) }
    var sideSpinnerString by remember { mutableStateOf(spinnerSideList.first()) }
    var showSideSpinner by remember { mutableStateOf(false) }

    showSideSpinner = when(locationSpinnerString) {
        "Center" -> {
            false
        } else -> {
            true
        }
    }


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
                    navController.popBackStack()
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
                .padding(bottom = 12.dp)
        )


        AnimatedVisibility(visible = showSideSpinner) {
            Column {
                Text(
                    "Side",
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                LocationDropDown(
                    list = spinnerSideList,
                    preselected = spinnerSideList.first(),
                    onSelectionChanged = {
                        sideSpinnerString = it
                    },
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                )
            }
        }

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
                .padding(bottom = 16.dp)
        )

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
                                side = if (showSideSpinner) sideSpinnerString else "Center",
                                shotsMade = shotsMade.toInt(),
                                totalShots = totalShots.toInt(),
                                location = locationSpinnerString,
                                range = rangeSpinnerString
                            )
                        )
                        navController.popBackStack()
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
                    modifier = Modifier.padding(start = 8.dp)
                )
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