package com.example.composetest.presentation.settings

sealed class SettingsEvent {
    data class ToggleShowTips(val showTips: Boolean = true): SettingsEvent()
}