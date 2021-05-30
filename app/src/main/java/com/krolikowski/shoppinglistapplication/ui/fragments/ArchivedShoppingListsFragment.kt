package com.krolikowski.shoppinglistapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.adapters.ShoppingListsAdapter
import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.fragment_archived_shopping_lists.*

class ArchivedShoppingListsFragment : Fragment(R.layout.fragment_archived_shopping_lists){

    lateinit var viewModel: ShoppingViewModel
    lateinit var listsAdapter: ShoppingListsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = ShoppingDatabase(requireContext())
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingListsAdapter(listOf(), viewModel)
        shoppingArchivedListsRecycleView.layoutManager = LinearLayoutManager(requireContext())
        shoppingArchivedListsRecycleView.adapter = adapter

        viewModel.getArchiveShoppingLists().observe(viewLifecycleOwner, {
            adapter.lists = it
            adapter.notifyDataSetChanged()
        })
    }
}