package com.example.coroutinelaunch.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutinelaunch.R
import com.example.coroutinelaunch.databinding.FragmentCoffeesBinding
import com.example.coroutinelaunch.network.GoalRepository
import com.example.coroutinelaunch.ui.adapter.CoffeeListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CoffeesFragment : Fragment(R.layout.fragment_coffees) {

    private lateinit var binding: FragmentCoffeesBinding
    private val viewModel: CoffeesViewModel = CoffeesViewModel(
        repo = GoalRepository(),
        Dispatchers.IO
    )
    private val listAdapter: CoffeeListAdapter by lazy { CoffeeListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCoffeesBinding.inflate(layoutInflater)

        setupView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchGoals()
    }

    private fun setupView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = listAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.coffeesFlow.collect { coffees ->
                    listAdapter.coffeeList = coffees
                }
            }
        }
    }
}