package com.example.shotlogger.presentation.settings

sealed class SettingsEvent {
    data class ToggleShowTips(val showTips: Boolean = true): SettingsEvent()
}