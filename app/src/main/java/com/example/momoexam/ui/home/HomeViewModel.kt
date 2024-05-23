package com.example.momoexam.ui.home

import androidx.lifecycle.ViewModel
import com.example.momoexam.data.OpenDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: OpenDataRepository
) : ViewModel() {

}