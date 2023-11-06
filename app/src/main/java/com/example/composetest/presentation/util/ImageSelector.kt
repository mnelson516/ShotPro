package com.example.composetest.presentation.util

import com.example.composetest.R
import com.example.composetest.presentation.model.Exercise

object ImageSelector {
    fun getImage(exercise: Exercise): Int {
        when (exercise.side) {
            "Center" -> {
                when (exercise.range) {
                    "Close Range" -> {
                        return R.drawable.center_close
                    }
                    "Mid Range" -> {
                        return R.drawable.center_mid
                    }
                    "Three Pointer" -> {
                        return R.drawable.center_three
                    }
                }
            }
            "Right" -> {
                when (exercise.location) {
                    "Baseline" -> {
                        when (exercise.range) {
                            "Close Range" -> {
                                return R.drawable.baseline_close_right
                            }
                            "Mid Range" -> {
                                return R.drawable.baseline_mid_right
                            }
                            "Three Pointer" -> {
                                return R.drawable.baseline_three_right
                            }
                        }
                    }
                    "Diagonal" -> {
                        when (exercise.range) {
                            "Close Range" -> {
                                return R.drawable.diagonal_close_right
                            }
                            "Mid Range" -> {
                                return R.drawable.diagonal_mid_right
                            }
                            "Three Pointer" -> {
                                return R.drawable.diagonal_three_right
                            }
                        }
                    }
                    "Elbow" -> {
                        return R.drawable.elbow_mid_right
                    }
                }
            }
            "Left" -> {
                when (exercise.location) {
                    "Baseline" -> {
                        when (exercise.range) {
                            "Close Range" -> {
                                return R.drawable.baseline_close_left
                            }
                            "Mid Range" -> {
                                return R.drawable.baseline_mid_left
                            }
                            "Three Pointer" -> {
                                return R.drawable.baseline_three_left
                            }
                        }
                    }
                    "Diagonal" -> {
                        when (exercise.range) {
                            "Close Range" -> {
                                return R.drawable.diagonal_close_left
                            }
                            "Mid Range" -> {
                                return R.drawable.diagonal_mid_left
                            }
                            "Three Pointer" -> {
                                return R.drawable.diagonal_three_left
                            }
                        }
                    }
                    "Elbow" -> {
                        return R.drawable.elbow_mid_left
                    }
                }
            }
        }

        return R.drawable.center_close
    }
}