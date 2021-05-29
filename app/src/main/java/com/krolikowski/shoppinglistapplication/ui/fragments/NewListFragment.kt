package com.krolikowski.shoppinglistapplication.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.adapters.ShoppingItemsAdapter
import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.fragment_new_list.*

class NewListFragment: Fragment(R.layout.fragment_new_list) {

    lateinit var viewModel: ShoppingViewModel
    private val args: NewListFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = ShoppingDatabase(requireContext())
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        amountPicker.apply {
            minValue = 1
            maxValue = 100
            wrapSelectorWheel = true
        }

        val currentList = viewModel.getCurrentList(args.listId)

        //list_name_TextView.text = currentList.name.toString()

        addItemButton.setOnClickListener {
            val name = itemNameEditText.text.toString()
            val amount = amountPicker.value
            val NewItem = ShoppingItem(0, name, amount, 0)
            viewModel.upsertItem(NewItem)
            itemNameEditText.setText("")

        }

        val adapter = ShoppingItemsAdapter(listOf(), viewModel)
        itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemsRecyclerView.adapter = adapter

        viewModel.getAllShoppingItems().observe(viewLifecycleOwner, {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })


    }

}