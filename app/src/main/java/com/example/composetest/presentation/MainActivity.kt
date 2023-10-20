package com.example.composetest.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composetest.R
import com.example.composetest.presentation.AddExercise.AddExerciseScreen
import com.example.composetest.presentation.AddWorkout.AddExerciseEvent
import com.example.composetest.presentation.AddWorkout.AddWorkoutViewModel
import com.example.composetest.presentation.AddWorkout.WorkoutScreen
import com.example.composetest.presentation.history.HistoryScreen
import com.example.composetest.presentation.history.HistoryViewModel
import com.example.composetest.presentation.model.Exercise
import com.example.composetest.presentation.theme.ComposeTestTheme
import com.example.composetest.presentation.theme.NeonOrange
import com.example.composetest.presentation.theme.SecondaryBlue
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
    val showBottomBar = rememberSaveable { (mutableStateOf(true)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    when (navBackStackEntry?.destination?.route) {
        "add_workout" -> {
            showBottomBar.value = false
        }
        "home" -> {
            showBottomBar.value = true
        }
        "history" -> {
            showBottomBar.value = true
        }
        "insights" -> {
            showBottomBar.value = true
        }
        "settings" -> {
            showBottomBar.value = true
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar.value) {
                BottomAppBar(
                    modifier = Modifier
                        .height(65.dp)
                        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                    cutoutShape = CircleShape,
                    backgroundColor = SecondaryBlue,
                    elevation = 22.dp
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
                        navController.navigate("add_workout")
                    },
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add icon")
                }
            }
        }
    ) {
        NavigationGraph(navController = navController, viewModel, historyViewModel)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, viewModel: AddWorkoutViewModel, historyViewModel: HistoryViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(navController)
        }
        composable(BottomNavItem.History.screen_route) {
            HistoryScreen(navController, historyViewModel)
        }
        composable(BottomNavItem.Insights.screen_route) {
            InsightsScreen(navController)
        }
        composable(BottomNavItem.Settings.screen_route) {
            SettingsScreen(navController)
        }
        composable("add_workout")
         {
             val exercise = it.savedStateHandle.get<Exercise>("exercise")
             WorkoutScreen(viewModel.state.value, viewModel::onEvent, navController)
        }
        composable("add_exercise")
        {
             AddExerciseScreen(
                updateExercise = { exercise ->
                    viewModel.onEvent(AddExerciseEvent.AddExercise(exercise))
                },
                navController = navController
             )
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
        modifier = Modifier
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .height(100.dp),
        //backgroundColor = Color.White,
        elevation = 0.dp,
        backgroundColor = SecondaryBlue
    ) {
        items.forEach {
            BottomNavigationItem(
                icon = {
                    it.icon?.let {
                        Icon(
                            painterResource(id = it),
                            contentDescription = "",
                            modifier = Modifier.size(35.dp),
                            tint = Color.White
                        )
                    }
                },
                label = {
                    it.title.let {
                        Text(
                            text = it,
                            color = Color.White
                        )
                    }
                },
                selected = currentRoute?.hierarchy?.any { it.route == it.route } == true,
                selectedContentColor = colorResource(R.color.purple_200),
                unselectedContentColor = Color.White.copy(alpha = 0.4f),
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
    }
}