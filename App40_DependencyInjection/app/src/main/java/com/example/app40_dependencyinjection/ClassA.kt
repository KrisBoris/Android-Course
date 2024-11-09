package com.example.app40_dependencyinjection

import android.util.Log
import javax.inject.Inject

class ClassA @Inject constructor() {
    fun startClassA() {
        Log.i("TAG", "Class A is created")
    }
}