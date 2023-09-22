package com.example.composetest.util

import com.example.composetest.R

sealed class BottomNavItem(var title: String, var icon: Int?, var screen_route: String){

    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object History: BottomNavItem("History", R.drawable.ic_history,"history")
    object Insights: BottomNavItem("Insights", R.drawable.ic_insights,"insights")
    object Settings: BottomNavItem("Settings", R.drawable.ic_settings, "settings")

    object Blank: BottomNavItem("", null, "")

}