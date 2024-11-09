package com.example.app40_dependencyinjection

import android.util.Log
import javax.inject.Inject

// Without DI
//class ClassC {
//    private val classA = ClassA()
//    private val classB = ClassB()
//
//    fun startClassC() {
//        classA.startClassA()
//        classB.startClassB()
//        Log.i("TAG", "Class C is created")
//    }
//}

// Field Injection
//class ClassC {
//    lateinit var classA: ClassA
//    lateinit var classB: ClassB
//
//    fun startClassC() {
//        classA.startClassA()
//        classB.startClassB()
//        Log.i("TAG", "Class C is created")
//    }
//}

// Constructor Injection
//class ClassC(private val classA: ClassA, private val classB: ClassB) {
//
//    fun startClassC() {
//        classA.startClassA()
//        classB.startClassB()
//        Log.i("TAG", "Class C is created")
//    }
//}

class ClassC @Inject constructor(private val classA: ClassA, private val classB: ClassB) {

    fun startClassC() {
        classA.startClassA()
        classB.startClassB()
        Log.i("TAG", "Class C is created")
    }
}