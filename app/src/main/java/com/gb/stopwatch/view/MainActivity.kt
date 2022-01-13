package com.gb.stopwatch.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.*
import com.gb.stopwatch.databinding.ActivityMainBinding
import com.gb.stopwatch.stopwatch.*
import com.gb.stopwatch.viewmodel.MainActivityViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getLiveData().observe(this, { renderData(it, binding) })

        initButtonListeners(binding)
    }

    private fun initButtonListeners(binding: ActivityMainBinding) {
        binding.buttonStartOne.setOnClickListener {
            viewModel.start()
        }
        binding.buttonPauseOne.setOnClickListener {
            viewModel.pause()
        }
        binding.buttonStopOne.setOnClickListener {
            viewModel.stop()
        }
    }

    private fun renderData(number: String, binding: ActivityMainBinding) {
        binding.textTimeOne.text = number
    }
}