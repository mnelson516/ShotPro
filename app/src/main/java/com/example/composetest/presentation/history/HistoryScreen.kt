package com.example.composetest.presentation.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetest.R
import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.domain.OrderType
import com.example.composetest.presentation.home.TitleSection
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.SecondaryBlue
import com.example.composetest.presentation.theme.Typography
import com.example.composetest.presentation.theme.WhiteBackground
import java.time.format.DateTimeFormatter

@Composable
fun HistoryScreen(viewModel: HistoryViewModel) {
    val state = viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            HistoryTopBar(onEvent = viewModel::onEvent)
        }
    ) {
        Box(modifier = Modifier
            .background(WhiteBackground)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.padding(top = 24.dp))
                FilterSection(
                    state,
                    onEvent = viewModel::onEvent,
                )

                HistoryExerciseList(exercises = state.value.exercises)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryTopBar(
    onEvent: (HistoryEvent) -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.history),
                style = Typography.h1
            )
        },
        actions = {
            IconButton(onClick = {
                onEvent(HistoryEvent.ShowFilters)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    tint = Color.Companion.White,
                    contentDescription = "Filter Button"
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = NavyBlue,
            scrolledContainerColor = NavyBlue,
            actionIconContentColor = NavyBlue,
            navigationIconContentColor = NavyBlue,
            titleContentColor = NavyBlue
        )
    )
}

@Composable
fun FilterSection(
    state: State<HistoryState>,
    onEvent: (HistoryEvent) -> Unit
) {
    Column {
        AnimatedVisibility(
            visible = state.value.showFilters,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            CategoryFilters(onEvent = onEvent)
        }
        AnimatedVisibility(
            visible = state.value.currentCategory != OrderType.Default && state.value.showFilters,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            when (state.value.currentCategory) {
                OrderType.Date -> SpecificFilters(
                    onEvent = onEvent,
                    items = listOf()
                )
                OrderType.Default -> SpecificFilters(
                    onEvent = onEvent,
                    items = listOf()
                )
                OrderType.Location -> SpecificFilters(
                    onEvent = onEvent,
                    items = listOf("Baseline", "Elbow", "Diagonal", "Center")
                )
                OrderType.Range -> SpecificFilters(
                    onEvent = onEvent,
                    items = listOf("Close Range", "Mid Range", "Three Pointer")
                )
                OrderType.Side -> SpecificFilters(
                    onEvent = onEvent,
                    items = listOf("Right", "Left")
                )
            }
        }
    }
}

@Composable
fun CategoryFilters(
    onEvent: (HistoryEvent) -> Unit,
)
 {
     val items = listOf("Range", "Side", "Angle")
     val selectedValue = remember { mutableStateOf("") }
     val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
     val onChangeState: (String) -> Unit = { selectedValue.value = it }

     Row(
         modifier = Modifier
             .padding(horizontal = 8.dp)
     ) {
         items.forEach {
             Row(
                 verticalAlignment = Alignment.CenterVertically,
                 modifier = Modifier
                     .selectable(
                         selected = isSelectedItem(it),
                         onClick = {
                             when (it) {
                                 "Range" -> {
                                     onEvent(HistoryEvent.SelectCategory(OrderType.Range))
                                 }

                                 "Side" -> {
                                     onEvent(HistoryEvent.SelectCategory(OrderType.Side))
                                 }

                                 "Angle" -> {
                                     onEvent(HistoryEvent.SelectCategory(OrderType.Location))
                                 }
                             }
                             onChangeState(it)
                         },
                         role = Role.RadioButton
                     )
                     .padding(8.dp)
             ) {
                 RadioButton(
                     selected = isSelectedItem(it),
                     colors = RadioButtonDefaults.colors(
                         selectedColor = Color.White,
                         unselectedColor = Color.White
                     ),
                     onClick = null
                 )
                 Text(
                     text = it,
                     color = Color.White,
                     modifier = Modifier
                         .padding(start = 4.dp)
                 )
             }
         }
     }
}

@Composable
fun SpecificFilters(
    onEvent: (HistoryEvent) -> Unit,
    items: List<String>
) {
    val selectedValue = remember { mutableStateOf("") }
    val isSelectedItem: (String) -> Boolean = { selectedValue.value == it }
    val onChangeState: (String) -> Unit = { selectedValue.value = it }

    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        items.forEach {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = isSelectedItem(it),
                        onClick = {
                            when (it) {
                                "Close Range" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.Center(OrderType.Range),
                                            "Close Range"
                                        )
                                    )
                                }

                                "Mid Range" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.MidRange(OrderType.Range),
                                            "Mid Range"
                                        )
                                    )
                                }

                                "Three Pointer" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.ThreePointRange(OrderType.Range),
                                            "Three Pointer"
                                        )
                                    )
                                }

                                "Baseline" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.Baseline(OrderType.Location),
                                            "Baseline"
                                        )
                                    )
                                }

                                "Center" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.Center(OrderType.Location),
                                            "Center"
                                        )
                                    )
                                }

                                "Diagonal" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.Diagonal(OrderType.Location),
                                            "Diagonal"
                                        )
                                    )
                                }

                                "Elbow" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.Elbow(OrderType.Location),
                                            "Elbow"
                                        )
                                    )
                                }

                                "Right" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.Right(OrderType.Side),
                                            "Right"
                                        )
                                    )
                                }

                                "Left" -> {
                                    onEvent(
                                        HistoryEvent.GetExercises(
                                            ExerciseOrder.Left(OrderType.Side),
                                            "Left"
                                        )
                                    )
                                }
                            }
                            onChangeState(it)
                        },
                        role = Role.RadioButton
                    )
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = isSelectedItem(it),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.White,
                        unselectedColor = Color.White
                    ),
                    onClick = null
                )
                Text(
                    text = it,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 4.dp)
                )
            }
        }
    }
}
@Composable
fun HistoryExerciseList(exercises: List<Exercise>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(exercises) {exercise ->
            HistoryExerciseCard(exercise = exercise)
        }
    }
}

@Composable
fun HistoryExerciseCard(exercise: Exercise) {
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
            Text(
                text = exercise.date.format(DateTimeFormatter.ISO_DATE),
                style = Typography.h5,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
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

        if (exercise.location != "Center") {
            Row(
                modifier = Modifier
                    .padding(top = 6.dp)
            ) {
                Text(
                    text = "Side: ",
                    style = Typography.h5,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 14.sp
                )
                Text(
                    text = exercise.side,
                    style = Typography.h5,
                    fontSize = 14.sp
                )
            }
        }
    }
}


