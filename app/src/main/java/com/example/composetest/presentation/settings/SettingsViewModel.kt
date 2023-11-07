package com.example.composetest.presentation.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composetest.data.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
): ViewModel() {

    private val _state = MutableStateFlow(UserPreferences())
    val state = _state.asStateFlow()

    private object PreferencesKeys {
        val TIPS_ENABLED = booleanPreferencesKey("show_completed")
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ToggleShowTips -> {
                updateShowTips(event.showTips)
            }
        }
    }
    fun getTipPreference() {
        viewModelScope.launch {
            val preferences = dataStore.data.first()
            _state.value = state.value.copy(
                tipsEnabled = preferences[PreferencesKeys.TIPS_ENABLED] ?: true
            )
        }
    }

     fun updateShowTips(tipsEnabled: Boolean) {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                preferences[PreferencesKeys.TIPS_ENABLED] = tipsEnabled
            }
            _state.value = state.value.copy(
                tipsEnabled = tipsEnabled
            )
        }
    }
}