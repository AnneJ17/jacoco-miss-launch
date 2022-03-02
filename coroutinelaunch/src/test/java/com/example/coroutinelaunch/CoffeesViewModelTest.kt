package com.example.coroutinelaunch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coroutinelaunch.model.CoffeeItem
import com.example.coroutinelaunch.network.GoalAPI
import com.example.coroutinelaunch.network.GoalRepository
import com.example.coroutinelaunch.ui.fragments.CoffeesViewModel
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
class CoffeesViewModelTest {

    @get:Rule
    val task = InstantTaskExecutorRule()

    private lateinit var viewModel: CoffeesViewModel

    private val repo: GoalRepository = mock()
    private val service: GoalAPI = mock()
    private val retrofit: Retrofit = mock()

    @Before
    fun setup() {
        viewModel = CoffeesViewModel(repo, Dispatchers.IO)
        Mockito.`when`(retrofit.create(GoalAPI::class.java)).thenReturn(service)
    }

    @Test
    fun `test fetch goal`() {
        TestCoroutineScope().runBlockingTest {
            Mockito.`when`(repo.getCoffees()).thenReturn(Response.success(listOf(COFFEE_ITEM)))
            viewModel.fetchGoals()
            val item = repo.getCoffees().body()?.get(0)
            assertEquals(COFFEE_ITEM, item)
        }
    }

    companion object {
        val COFFEE_ITEM = CoffeeItem(
            id = 1,
            title = "Black Coffee",
            ingredients = listOf("", "Home", ""),
            description = "Sugar Plum induced black coffee",
        )
    }
}