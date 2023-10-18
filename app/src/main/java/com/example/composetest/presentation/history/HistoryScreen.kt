package com.example.composetest.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetest.R
import com.example.composetest.presentation.TitleSection
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.theme.NavyBlue


@Composable
@Preview
fun HistoryScreen() {
    val viewModel = viewModel<HistoryViewModel>()
    val state = viewModel.state.value
    Scaffold {
        Box(modifier = Modifier
            .background(NavyBlue)
            .padding(it)
            .fillMaxSize()
        ) {
            Column {
                Spacer(modifier = Modifier.padding(top = 24.dp))
                HistoryTopBar()
                FilterSection(state.exercises)
            }
        }
    }
}

@Composable
fun HistoryTopBar() {
    val viewModel = viewModel<HistoryViewModel>()
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
                viewModel.onEvent(HistoryEvent.ShowFilters)
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
    items: List<Exercise>
) {

}

