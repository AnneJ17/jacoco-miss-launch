package com.example.coroutinelaunch.network

import com.example.coroutinelaunch.model.CoffeeItem
import retrofit2.Response

class GoalRepository {
    private val api = ApiClient().getRetrofitClientInstance().create(GoalAPI::class.java)

    suspend fun getCoffees(): Response<List<CoffeeItem>> = api.getCoffees()
}