package com.example.coroutinelaunch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinelaunch.R
import com.example.coroutinelaunch.databinding.ItemViewCoffeeBinding
import com.example.coroutinelaunch.model.CoffeeItem

class CoffeeListAdapter : RecyclerView.Adapter<CoffeeListAdapter.CoffeesViewHolder>() {

    var coffeeList: List<CoffeeItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_coffee, parent, false)

        return CoffeesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coffeeList.size
    }

    override fun onBindViewHolder(holder: CoffeesViewHolder, position: Int) {
        val item = coffeeList[position]
        holder.binding.apply {
            coffeeTitle.text = item.title
            coffeeDescription.text = item.description
        }
    }

    inner class CoffeesViewHolder(
        private val rootView: View,
        val binding: ItemViewCoffeeBinding = ItemViewCoffeeBinding.bind(rootView)
    ) : RecyclerView.ViewHolder(rootView)
}