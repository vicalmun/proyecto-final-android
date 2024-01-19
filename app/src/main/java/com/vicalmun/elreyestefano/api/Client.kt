package com.vicalmun.elreyestefano.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
object Client {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://stephen-king-api.onrender.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
