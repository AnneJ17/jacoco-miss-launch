package com.example.coroutinelaunch.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinelaunch.model.CoffeeItem
import com.example.coroutinelaunch.network.GoalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class CoffeesViewModel(
    private val repo: GoalRepository,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _coffees: MutableStateFlow<List<CoffeeItem>> = MutableStateFlow(emptyList())
    val coffeesFlow: Flow<List<CoffeeItem>> = _coffees

    fun fetchGoals() {
        viewModelScope.launch(ioDispatcher) {
            val response = repo.getCoffees()
            Timber.tag("Anne*").d("${response.body()}")
            response.body()?.let {
                _coffees.value = it
            }
        }
    }
}