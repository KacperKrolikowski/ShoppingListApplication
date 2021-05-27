package com.krolikowski.shoppinglistapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_list_item.view.*

class ShoppingListsAdapter(
    var lists: List<ShoppingList>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingListsAdapter.ShoppingListsViewHolder>() {

    inner class ShoppingListsViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.shopping_list_item,
            parent,
            false)
        return ShoppingListsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingListsViewHolder, position: Int) {
        val currentShoppingList = lists[position]

        holder.itemView.shoppingListName.text = currentShoppingList.name

    }

    override fun getItemCount(): Int {
        return lists.size
    }
}