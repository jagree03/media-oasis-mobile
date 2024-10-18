package com.example.jagree03.mediaoasis

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val data = MutableLiveData<String>()
}