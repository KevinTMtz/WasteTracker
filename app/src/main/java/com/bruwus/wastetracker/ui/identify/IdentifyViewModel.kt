package com.bruwus.wastetracker.ui.identify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IdentifyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is identify Fragment"
    }
    val text: LiveData<String> = _text
}