package com.gb.stopwatch.repository

import com.gb.stopwatch.stopwatch.StopwatchListOrchestrator
import kotlinx.coroutines.flow.StateFlow

class RepositoryImpl(private val stopwatchListOrchestrator: StopwatchListOrchestrator) :
    Repository {

    override fun start() {
        stopwatchListOrchestrator.start()
    }

    override fun stop() {
        stopwatchListOrchestrator.stop()
    }

    override fun pause() {
        stopwatchListOrchestrator.pause()
    }

    override fun getTicker() =
        stopwatchListOrchestrator.ticker
}

interface Repository {
    fun start()
    fun stop()
    fun pause()
    fun getTicker(): StateFlow<String>
}