package com.gb.stopwatch.stopwatch

interface TimestampProvider {
    fun getMilliseconds(): Long
}