package com.example.shotlogger.presentation.util

import com.example.shotlogger.presentation.model.FieldGoalData
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PercentageConverterTest{
    @Test
    fun `valid percentage happy path`() {
        val result = PercentageConverter.convertToPercentage(
            10,
            20
        )
        assertThat(result).matches("50")
    }

    @Test
    fun `valid percentage zeros`() {
        val result = PercentageConverter.convertToPercentage(
            0,
            20
        )
        assertThat(result).matches("0")
    }

    @Test
    fun `invalid percentage zero shots taken`() {
        val result = PercentageConverter.convertToPercentage(
            5,
            0
        )
        assertThat(result).matches("0")
    }

    @Test
    fun `invalid percentage more made than shots taken`() {
        val result = PercentageConverter.convertToPercentage(
            5,
            2
        )
        assertThat(result).matches("0")
    }

    @Test
    fun `max percentage close range`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                0,
                1,
                1,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0
            )
        )
        assertThat(result.first).matches("Close Range")
        assertThat(result.second).matches("100")
    }
    @Test
    fun `max percentage mid range`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                1,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0
            )
        )
        assertThat(result.first).matches("Mid Range")
        assertThat(result.second).matches("100")
    }

    @Test
    fun `max percentage three`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                1,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0
            )
        )
        assertThat(result.first).matches("Three Pointer")
        assertThat(result.second).matches("100")
    }

    @Test
    fun `max percentage left`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                1,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0
            )
        )
        assertThat(result.first).matches("Left Side")
        assertThat(result.second).matches("100")
    }

    @Test
    fun `max percentage right`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                1,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0
            )
        )
        assertThat(result.first).matches("Right Side")
        assertThat(result.second).matches("100")
    }

    @Test
    fun `max percentage baseline`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                1,
                1,
                0,
                1,
                0,
                1,
                0
            )
        )
        assertThat(result.first).matches("Baseline")
        assertThat(result.second).matches("100")
    }

    @Test
    fun `max percentage diagonal`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                1,
                1,
                0,
                1,
                0
            )
        )
        assertThat(result.first).matches("Diagonal")
        assertThat(result.second).matches("100")
    }

    @Test
    fun `max percentage elbow`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                1,
                1,
                0
            )
        )
        assertThat(result.first).matches("Elbow")
        assertThat(result.second).matches("100")
    }

    @Test
    fun `max percentage center`() {
        val result = PercentageConverter.getBestPercentage(
            FieldGoalData(
                0,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                0,
                1,
                1
            )
        )
        assertThat(result.first).matches("Center")
        assertThat(result.second).matches("100")
    }

}