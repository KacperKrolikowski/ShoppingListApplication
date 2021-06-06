package com.krolikowski.shoppinglistapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.adapters.ShoppingListsAdapter
import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import com.krolikowski.shoppinglistapplication.ui.others.AddDialogListener
import com.krolikowski.shoppinglistapplication.ui.others.AddNewShoppingListDialog
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ListsViewModel
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_active_shopping_lists.*

@AndroidEntryPoint
class ActiveShoppingListsFragment: Fragment(R.layout.fragment_active_shopping_lists) {

    private val listsViewModel: ListsViewModel by activityViewModels()
    lateinit var listsAdapter: ShoppingListsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ShoppingListsAdapter(listOf(), listsViewModel)
        shoppingListsRecycleView.layoutManager = LinearLayoutManager(requireContext())
        shoppingListsRecycleView.adapter = adapter

        listsViewModel.getActiveShoppingLists().observe(viewLifecycleOwner, Observer{
            adapter.lists = it
            adapter.notifyDataSetChanged()
        })

    }
}