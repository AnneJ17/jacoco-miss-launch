package com.example.coroutinelaunch.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coffee(
    val coffeeList: List<CoffeeItem>
) : Parcelable

@Parcelize
data class CoffeeItem(
    val description: String,
    val id: Int,
    val ingredients: List<String>,
    val title: String
) : Parcelable