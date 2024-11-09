package com.example.app40_dependencyinjection

import dagger.Component

@Component(modules = [ClassABModule::class])
interface ClassCComponent {

    fun getClassCInstance(): ClassC
}