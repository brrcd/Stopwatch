package com.gb.stopwatch.viewmodel

import androidx.lifecycle.*
import com.gb.stopwatch.repository.Repository
import com.gb.stopwatch.repository.RepositoryImpl
import com.gb.stopwatch.stopwatch.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    private val repository = RepositoryImpl(stopwatchListOrchestrator)
    private val liveData: LiveData<String> = repository.getTicker().asLiveData()

    fun getLiveData() = liveData

    fun start() {
        repository.start()
    }

    fun pause() {
        repository.pause()
    }

    fun stop() {
        repository.stop()
    }
}