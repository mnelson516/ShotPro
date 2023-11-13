package com.example.shotlogger.domain

sealed class OrderType {
    object Default: OrderType()
    object Range: OrderType()
    object Location: OrderType()
    object Date: OrderType()
    object Side: OrderType()
}
