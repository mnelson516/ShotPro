package com.example.shotlogger.presentation.util

import org.junit.Test
import com.google.common.truth.Truth.assertThat


class InputValidatorTest {

    @Test
    fun `valid exercise input happy path`() {
        val result = InputValidator.isValidExerciseInput(
            "test",
            "1",
            "2"
        )
        assertThat(result).matches(InputValidator.VALID_INPUT)
    }

    @Test
    fun `invalid exercise input empty field`() {
        val result = InputValidator.isValidExerciseInput(
            "test",
            "",
            "2"
        )
        assertThat(result).matches(InputValidator.EMPTY_FIELD)
    }

    @Test
    fun `invalid exercise input invalid shots`() {
        val result = InputValidator.isValidExerciseInput(
            "test",
            "6",
            "2"
        )
        assertThat(result).matches(InputValidator.INVALID_INPUT)
    }
}