package com.example.composetest.domain

sealed class ExerciseOrder(val orderType: OrderType) {
    class Default(orderType: OrderType): ExerciseOrder(orderType)
    class Range(orderType: OrderType): ExerciseOrder(orderType)
    class Location(orderType: OrderType): ExerciseOrder(orderType)
    class Date(orderType: OrderType): ExerciseOrder(orderType)
    class Angle(orderType: OrderType): ExerciseOrder(orderType)

    fun copy(orderType: OrderType): ExerciseOrder {
        return when(this) {
            is Default -> Default(orderType)
            is Range -> Range(orderType)
            is Date -> Date(orderType)
            is Location -> Location(orderType)
            is Angle -> Angle(orderType)
        }
    }
}
