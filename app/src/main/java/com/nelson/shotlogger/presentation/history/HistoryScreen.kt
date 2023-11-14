package com.nelson.shotlogger.presentation.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nelson.shotlogger.R
import com.nelson.shotlogger.domain.ExerciseOrder
import com.nelson.shotlogger.domain.OrderType
import com.nelson.shotlogger.presentation.insights.EmptyScreenState
import com.nelson.shotlogger.presentation.model.Exercise
import com.nelson.shotlogger.presentation.theme.NavyBlue
import com.nelson.shotlogger.presentation.theme.SecondaryBlue
import com.nelson.shotlogger.presentation.theme.Typography
import com.nelson.shotlogger.presentation.theme.WhiteBackground
import com.nelson.shotlogger.presentation.util.Category
import com.nelson.shotlogger.presentation.util.DateFormatter
import java.time.LocalDateTime
import java.util.Locale

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel,
    navController: NavController
) {
    viewModel.onEvent(
        HistoryEvent.InitialExercises(
            ExerciseOrder.Default(OrderType.Default),
        )
    )
    val state = viewModel.state.collectAsState()
    HistoryScreenContent(onEvent = viewModel::onEvent, state, navController)
}

@Composable
fun HistoryScreenContent(
    onEvent: (HistoryEvent) -> Unit,
    state: State<HistoryState>,
    navController: NavController
) {
    Scaffold(
        topBar = {
            HistoryTopBar(onEvent = onEvent)
        }
    ) {
        Box(modifier = Modifier
            .background(WhiteBackground)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                FilterSection(
                    state,
                    onEvent = onEvent,
                )
                val list = state.value.exercises.groupBy { exercise ->
                    DateFormatter.formatDate(exercise.date, LocalDateTime.now())
                }.toSortedMap()
                val categoryList = list.map { map ->
                    Category(
                        date = map.key.toString(),
                        items = map.value
                    )
                }
                if (list.isEmpty()) {
                    EmptyScreenState()
                } else {
                    CategorizedLazyColumn(
                        categories = categoryList,
                        modifier = Modifier.padding(bottom = 60.dp),
                        navController = navController,
                        onEvent = onEvent
                    )
                }
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavyBlue)
    ) {
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
fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text.lowercase().titlecaseFirstCharIfItIsLowercase(),
        fontSize = 16.sp,
        style = Typography.h3,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(SecondaryBlue)
            .padding(16.dp)
    )
}

@Composable
private fun CategoryItem(
    exercise: Exercise,
    navController: NavController,
    onEvent: (HistoryEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onEvent(HistoryEvent.SetDetails(exercise))
                navController.navigate("History Details")
            }
    ) {
        Text(
            text = exercise.name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(16.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.forward_arrow),
            contentDescription = "Forward Arrow",
            tint = Color.DarkGray,
            modifier = modifier
                .padding(16.dp)
                .size(20.dp)
            )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories: List<Category>,
    modifier: Modifier = Modifier,
    navController: NavController,
    onEvent: (HistoryEvent) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(category.date)
            }
            items(category.items) { exercise ->
                CategoryItem(exercise, navController, onEvent)
            }
        }
    }
}

fun String.titlecaseFirstCharIfItIsLowercase() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}


