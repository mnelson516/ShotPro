package com.nelson.shotlogger.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nelson.shotlogger.R
import com.nelson.shotlogger.presentation.theme.NavyBlue
import com.nelson.shotlogger.presentation.theme.Typography
import com.nelson.shotlogger.presentation.theme.WhiteBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.settings),
                        style = Typography.h1
                    )
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
    ) {
        Column(
            modifier = Modifier
                .background(WhiteBackground)
                .padding(it)
                .fillMaxSize()
        ) {
            SettingsContent(state, viewModel::onEvent)
        }
    }
}

@Composable
fun SettingsContent(
    state: State<UserPreferences>,
    onEvent: (SettingsEvent) -> Unit
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(id = R.string.tips_enabled),
            color = Color.Black,
            style = Typography.h2
        )
        Switch(
            checked = state.value.tipsEnabled,
            onCheckedChange = { tipsEnabled ->
                onEvent(SettingsEvent.ToggleShowTips(tipsEnabled))
            }
        )
    }
}