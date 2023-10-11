package com.example.composetest.presentation.util

import org.junit.Test
import com.google.common.truth.Truth.assertThat


class InputValidatorTest {

    @Test
    fun `valid exercise input happy path`() {
        val result = InputValidator.isValidExerciseInput(
            "1",
            "2"
        )
        assertThat(result).matches(InputValidator.VALID_INPUT)
    }

    @Test
    fun `invalid exercise input empty field`() {
        val result = InputValidator.isValidExerciseInput(
            "",
            "2"
        )
        assertThat(result).matches(InputValidator.EMPTY_FIELD)
    }

    @Test
    fun `invalid exercise input invalid shots`() {
        val result = InputValidator.isValidExerciseInput(
            "6",
            "2"
        )
        assertThat(result).matches(InputValidator.INVALID_INPUT)
    }
}