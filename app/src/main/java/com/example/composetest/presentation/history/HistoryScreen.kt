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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composetest.R
import com.example.composetest.domain.ExerciseOrder
import com.example.composetest.presentation.TitleSection
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.theme.NavyBlue
import java.time.LocalDateTime


@Composable
fun HistoryScreen(navController: NavController, viewModel: HistoryViewModel) {
    val state = viewModel.state.collectAsState()
    Scaffold {
        Box(modifier = Modifier
            .background(NavyBlue)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.padding(top = 24.dp))
                HistoryTopBar(
                    onEvent = viewModel::onEvent
                )
                FilterSection(
                    state,
                    onEvent = viewModel::onEvent,
                )
            }
        }
    }
}

@Composable
fun HistoryTopBar(
    onEvent: (HistoryEvent) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(0.5f)
        ) {
            TitleSection(title = stringResource(id = R.string.history))
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .weight(0.5f)
        ) {
            IconButton(onClick = {
                onEvent(HistoryEvent.ShowFilters)
            }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    tint = Color.Companion.White,
                    contentDescription = "Filter Button"
                )
            }
            IconButton(onClick = {

            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    tint = Color.Companion.White,
                    contentDescription = "Filter Button"
                )
            }
        }
    }
}

@Composable
fun FilterSection(
    state: State<HistoryState>,
    onEvent: (HistoryEvent) -> Unit) {
    AnimatedVisibility(
        visible = state.value.showFilters,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            DefaultRadioButton(
//                text = "Location",
//                selected = exerciseOrder is ExerciseOrder.Location,
//                onSelect = {  }
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            DefaultRadioButton(
//                text = "Range",
//                selected = exerciseOrder is ExerciseOrder.Range,
//                onSelect = {  }
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            DefaultRadioButton(
//                text = "Angle",
//                selected = exerciseOrder is ExerciseOrder.Angle,
//                onSelect = {  }
//            )
//        }
    }
}

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Cyan,
                unselectedColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = Color.White)
    }
}

