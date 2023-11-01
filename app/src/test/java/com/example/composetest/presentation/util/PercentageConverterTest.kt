package com.example.composetest.presentation.util

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
        assertThat(result).matches("INVALID INPUT")
    }

    @Test
    fun `invalid percentage more made than shots taken`() {
        val result = PercentageConverter.convertToPercentage(
            5,
            2
        )
        assertThat(result).matches("INVALID INPUT")
    }
}