package com.example.app39_retrofit

import com.google.gson.annotations.SerializedName

data class AlbumItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("title")
    val title: String)
