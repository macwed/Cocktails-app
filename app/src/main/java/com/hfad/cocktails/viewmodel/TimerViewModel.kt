package com.hfad.cocktails.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TimerViewModel : ViewModel() {

    private var _defaultTime = 60
    var timeLeft = mutableStateOf(_defaultTime)
        private set

    private var timerJob: Job? = null
    private var isPaused = false
    private var isInitialized = false

    fun initDefaultTime(newDefault: Int) {
        if (!isInitialized) {
            _defaultTime = newDefault
            timeLeft.value = newDefault
            isInitialized = true
        }
    }

    fun startTimer() {
        if (timerJob != null) return
        isPaused = false
        timerJob = viewModelScope.launch {
            while (timeLeft.value > 0 && !isPaused) {
                delay(1000)
                timeLeft.value = timeLeft.value - 1
            }
            if (timeLeft.value == 0) {
                timerJob?.cancel()
                timerJob = null
            }
        }
    }

    fun pauseTimer() {
        isPaused = true
        timerJob?.cancel()
        timerJob = null
    }

    fun resetTimer() {
        timerJob?.cancel()
        timerJob = null
        isPaused = false
        timeLeft.value = _defaultTime
    }

    fun setUserTime(newTime: Int) {
        timeLeft.value = newTime
        timerJob?.cancel()
        timerJob = null
        isPaused = false
    }
}
