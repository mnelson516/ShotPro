package com.example.shotlogger.presentation.util

import com.example.shotlogger.R

sealed class BottomNavItem(var title: String, var icon: Int?, var screen_route: String){

    object Home : BottomNavItem("Home", R.drawable.ic_home,"Home")
    object History: BottomNavItem("History", R.drawable.ic_history,"History")
    object Insights: BottomNavItem("Insights", R.drawable.ic_insights,"Insights")
    object Settings: BottomNavItem("Settings", R.drawable.ic_settings, "Settings")
    object Blank: BottomNavItem("", null, "")

}