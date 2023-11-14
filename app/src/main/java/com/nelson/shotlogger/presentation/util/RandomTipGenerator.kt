package com.nelson.shotlogger.presentation.util

import android.content.Context
import com.nelson.shotlogger.R

class RandomTipGenerator(context: Context) {

    val list = listOf(
        context.getString(R.string.one_motion),
        context.getString(R.string.form_shooting),
        context.getString(R.string.no_perfect),
        context.getString(R.string.on_each_miss),
        context.getString(R.string.resist_fatigue),
        context.getString(R.string.try_filming),
        context.getString(R.string.balance),
        context.getString(R.string.eyes_on_rim),
        context.getString(R.string.confidence),
        context.getString(R.string.consistency),
        context.getString(R.string.shape),
        context.getString(R.string.full_speed),
        context.getString(R.string.mental_toughness),
        context.getString(R.string.reflect),
        context.getString(R.string.better_shooters),
        context.getString(R.string.sleep),
        context.getString(R.string.fast_release),
    )

    fun generateRandomTip(): String {
        return list.random()
    }
}