package com.example.coroutinelaunch.network

import com.example.coroutinelaunch.model.CoffeeItem
import retrofit2.Response
import retrofit2.http.GET

interface GoalAPI {

    @GET("coffee/hot")
    suspend fun getCoffees(): Response<List<CoffeeItem>>

}