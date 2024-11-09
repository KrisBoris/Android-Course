package com.example.app40_dependencyinjection

import android.util.Log
import javax.inject.Inject

class ClassB @Inject constructor() {
    fun startClassB() {
        Log.i("TAG", "Class B is created")
    }
}