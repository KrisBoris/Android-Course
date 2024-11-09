package com.example.app40_dependencyinjection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Without DI
//        val classC = ClassC()
//        classC.startClassC()

        // Field Injection
//        val classC = ClassC()
//        classC.classA.startClassA()
//        classC.classB.startClassB()
//        classC.startClassC()

        // Constructor Injection
//        val classA = ClassA()
//        val classB = ClassB()
//        val classC = ClassC(classA, classB)
//        classC.startClassC()

        DaggerClassCComponent.create().getClassCInstance().startClassC()
    }
}