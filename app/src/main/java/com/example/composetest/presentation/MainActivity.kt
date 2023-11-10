package com.example.composetest.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetest.R
import com.example.composetest.presentation.addexercise.AddExerciseScreen
import com.example.composetest.presentation.addworkout.AddExerciseEvent
import com.example.composetest.presentation.addworkout.AddWorkoutViewModel
import com.example.composetest.presentation.addworkout.WorkoutScreen
import com.example.composetest.presentation.insights.InsightsScreen
import com.example.composetest.presentation.history.HistoryScreen
import com.example.composetest.presentation.history.HistoryScreenDetails
import com.example.composetest.presentation.history.HistoryViewModel
import com.example.composetest.presentation.home.HomeScreen
import com.example.composetest.presentation.insights.GraphScreen
import com.example.composetest.presentation.insights.InsightsViewModel
import com.example.composetest.presentation.settings.SettingsScreen
import com.example.composetest.presentation.settings.SettingsViewModel
import com.example.composetest.presentation.theme.NavyBlue
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.util.BottomNavItem
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController: SystemUiController = rememberSystemUiController()
            systemUiController.setStatusBarColor(color = colorResource(id = R.color.navy_blue))
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                MainScreenView()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    val viewModel = viewModel<AddWorkoutViewModel>()
    val historyViewModel = viewModel<HistoryViewModel>()
    val insightsViewModel = viewModel<InsightsViewModel>()
    val settingsViewModel = viewModel<SettingsViewModel>()
    settingsViewModel.getTipPreference()
    val showBottomBar = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        "Add Workout" -> {
            showBottomBar.value = false
        }
        "Home" -> {
            showBottomBar.value = true
        }
        "History" -> {
            showBottomBar.value = true
        }
        "Insights" -> {
            showBottomBar.value = true
        }
        "Settings" -> {
            showBottomBar.value = true
        }
        "Graph Screen" -> {
            showBottomBar.value = false
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar.value) {
                BottomAppBar(
                    backgroundColor = NavyBlue,
                    modifier = Modifier
                        .height(65.dp)
                        .fillMaxWidth(),
                    cutoutShape = CircleShape,
                ) {
                    BottomNavigationBar(navController = navController)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (showBottomBar.value) {
                FloatingActionButton(
                    shape = CircleShape,
                    backgroundColor = NeonOrange,
                    onClick = {
                        navController.navigate("Add Workout")
                    },
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
                }
            }
        }
    ) {
        NavigationGraph(
            navController = navController,
            viewModel,
            historyViewModel,
            insightsViewModel,
            settingsViewModel
        )
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: AddWorkoutViewModel,
    historyViewModel: HistoryViewModel,
    insightsViewModel: InsightsViewModel,
    settingsViewModel: SettingsViewModel
) {
    NavHost(
        navController,
        startDestination = BottomNavItem.Home.screen_route,
        ) {
        composable(
            BottomNavItem.Home.screen_route,
            enterTransition = {EnterTransition.None},
            exitTransition = { ExitTransition.None}
        ) {
            HomeScreen(insightsViewModel)
            viewModel.onEvent(AddExerciseEvent.ClearExercises)
            viewModel.onEvent(AddExerciseEvent.ShowDialog(true))
        }
        composable(
            BottomNavItem.History.screen_route,
            enterTransition = {EnterTransition.None},
            exitTransition = { ExitTransition.None}
        ) {
            HistoryScreen(historyViewModel, navController)
            viewModel.onEvent(AddExerciseEvent.ClearExercises)
            viewModel.onEvent(AddExerciseEvent.ShowDialog(true))
        }
        composable(
            BottomNavItem.Insights.screen_route,
            enterTransition = {EnterTransition.None},
            exitTransition = { ExitTransition.None}
        ) {
            InsightsScreen(insightsViewModel, historyViewModel, navController)
            viewModel.onEvent(AddExerciseEvent.ClearExercises)
            viewModel.onEvent(AddExerciseEvent.ShowDialog(true))
        }
        composable(
            BottomNavItem.Settings.screen_route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None}
        ) {
            SettingsScreen(settingsViewModel)
            viewModel.onEvent(AddExerciseEvent.ClearExercises)
            viewModel.onEvent(AddExerciseEvent.ShowDialog(true))
        }
        composable(
            "Add Workout",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None })
         {
             WorkoutScreen(viewModel.state.value, viewModel::onEvent, navController, settingsViewModel)
        }
        composable(
            "Add Exercise",
            enterTransition = { slideInVertically() + fadeIn() },
            exitTransition = { slideOutVertically() + fadeOut() }
        )
        {
             AddExerciseScreen(
                updateExercise = { exercise ->
                    viewModel.onEvent(AddExerciseEvent.AddExercise(exercise))
                },
                navController = navController
             )
        }
        composable(
            "History Details",
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() }
        ) {
            HistoryScreenDetails(historyViewModel.state.collectAsState(), navController)
        }
        composable(
            "Graph Screen",
            enterTransition = { slideInHorizontally(initialOffsetX = { it }) + fadeIn() },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }) + fadeOut() }
        ) {
            GraphScreen(navController = navController, historyViewModel, insightsViewModel)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.History,
        BottomNavItem.Blank,
        BottomNavItem.Insights,
        BottomNavItem.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    BottomNavigation(
        backgroundColor = NavyBlue,
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach {
            currentRoute?.route?.equals(it.screen_route)?.let { it1 ->
                BottomNavigationItem(
                    icon = {
                        it.icon?.let {
                            Icon(
                                painterResource(id = it),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp),
                                tint = Color.White
                            )
                        }
                    },
                    alwaysShowLabel = false,
                    label = {
                        Text(
                            text = it.title,
                            color = Color.White
                        )
                    },
                    selected = it1,
                    onClick = {
                        it.screen_route.let { it1 ->
                            if (it1.isNotEmpty()) {
                                navController.navigate(it1) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }

                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}