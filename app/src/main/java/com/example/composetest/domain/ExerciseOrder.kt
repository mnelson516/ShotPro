package com.example.composetest.domain

sealed class ExerciseOrder(val orderType: OrderType) {
    class Default(orderType: OrderType): ExerciseOrder(orderType)
    class CloseRange(orderType: OrderType): ExerciseOrder(orderType)
    class MidRange(orderType: OrderType): ExerciseOrder(orderType)
    class ThreePointRange(orderType: OrderType): ExerciseOrder(orderType)
    class Baseline(orderType: OrderType): ExerciseOrder(orderType)
    class Elbow(orderType: OrderType): ExerciseOrder(orderType)
    class Diagonal(orderType: OrderType): ExerciseOrder(orderType)
    class Center(orderType: OrderType): ExerciseOrder(orderType)
    class Left(orderType: OrderType): ExerciseOrder(orderType)
    class Right(orderType: OrderType): ExerciseOrder(orderType)

}
