package com.example.composetest.presentation.AddWorkout

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.composetest.di.AppModule
import com.example.composetest.presentation.HomeScreen
import com.example.composetest.presentation.MainActivity
import com.example.composetest.presentation.util.BottomNavItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@UninstallModules(AppModule::class)
class WorkoutScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.screen_route
            ) {
                composable(route = BottomNavItem.Home.screen_route) {
                    HomeScreen(navController = navController)
                }
            }

        }
    }

}