package com.example.app35_mvvm.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app35_mvvm.model.CalculateData

class CalculateViewModel: ViewModel() {

    fun calculateSum(num1: Int, num2: Int) : CalculateData {
        return CalculateData(num1, num2, num1 + num2)
    }
}