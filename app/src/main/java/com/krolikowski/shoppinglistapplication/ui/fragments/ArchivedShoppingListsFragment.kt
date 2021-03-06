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
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ListsViewModel
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shopping.*
import kotlinx.android.synthetic.main.fragment_archived_shopping_lists.*

@AndroidEntryPoint
class ArchivedShoppingListsFragment : Fragment(R.layout.fragment_archived_shopping_lists){

    private val listsViewModel: ListsViewModel by activityViewModels()

    lateinit var listsAdapter: ShoppingListsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ShoppingListsAdapter(listOf(), listsViewModel)
        shoppingArchivedListsRecycleView.layoutManager = LinearLayoutManager(requireContext())
        shoppingArchivedListsRecycleView.adapter = adapter

        listsViewModel.getArchiveShoppingLists().observe(viewLifecycleOwner, Observer{
            adapter.lists = it
            adapter.notifyDataSetChanged()
        })
    }
}