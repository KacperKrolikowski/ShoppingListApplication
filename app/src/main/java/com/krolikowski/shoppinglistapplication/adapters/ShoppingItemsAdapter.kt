package com.krolikowski.shoppinglistapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import com.krolikowski.shoppinglistapplication.ui.fragments.NewListFragmentDirections
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import kotlinx.android.synthetic.main.fragment_new_list.view.*
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingItemsAdapter(
var items: List<ShoppingItem>,
private var viewModel: ShoppingViewModel,
var isArchive: Int
): RecyclerView.Adapter<ShoppingItemsAdapter.ShoppingItemsViewHolder>() {

    inner class ShoppingItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.shopping_list_item,
            parent,
            false)
        return ShoppingItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingItemsViewHolder, position: Int) {

        val currentShoppingItem = items[position]

        if (isArchive == 1) {
            holder.itemView.plusButton.visibility = INVISIBLE
            holder.itemView.minusButton.visibility = INVISIBLE
        }

        holder.itemView.itemName.text = currentShoppingItem.name
        holder.itemView.itemAmount.text = currentShoppingItem.amount.toString()

        holder.itemView.isBoughtCheckBox.isChecked = currentShoppingItem.state != 0


        holder.itemView.plusButton.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsertItem(currentShoppingItem)
        }

        holder.itemView.minusButton.minusButton.setOnClickListener {
            if (currentShoppingItem.amount > 0){
                currentShoppingItem.amount--
                viewModel.upsertItem(currentShoppingItem)
            } else{
                viewModel.deleteItem(currentShoppingItem)
            }
        }

        holder.itemView.isBoughtCheckBox.setOnClickListener {
            if (currentShoppingItem.state == 0){
                currentShoppingItem.state = 1
                viewModel.upsertItem(currentShoppingItem)
            } else{
                currentShoppingItem.state = 0
                viewModel.upsertItem(currentShoppingItem)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}