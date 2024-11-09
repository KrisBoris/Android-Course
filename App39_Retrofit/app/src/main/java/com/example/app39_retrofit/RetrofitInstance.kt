package com.example.app39_retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        val mainURL = "https://jsonplaceholder.typicode.com/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                // Create Retrofit with given URL
                .baseUrl(mainURL)
                // Convert JSON data
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}