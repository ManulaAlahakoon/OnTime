package com.example.project_test_1.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    // LiveData for Lecture Name
    private val _lectureName = MutableLiveData<String>().apply {
        value = "Introduction to Android Development"
    }
    val lectureName: LiveData<String> = _lectureName

    // LiveData for Lecture Hall
    private val _lectureHall = MutableLiveData<String>().apply {
        value = "Hall A"
    }
    val lectureHall: LiveData<String> = _lectureHall

    // LiveData for Remaining Time Label
    private val _remainingTime = MutableLiveData<String>().apply {
        value = "1 hour 30 minutes"
    }
    val remainingTime: LiveData<String> = _remainingTime

    // LiveData for Countdown Time (in milliseconds)
    private val _countdownTime = MutableLiveData<Long>().apply {
        value = 90 * 60 * 1000 // 1 hour 30 minutes in milliseconds
    }
    val countdownTime: LiveData<Long> = _countdownTime
}
