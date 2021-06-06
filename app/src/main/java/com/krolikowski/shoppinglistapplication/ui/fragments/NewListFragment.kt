package com.krolikowski.shoppinglistapplication.ui.fragments

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.adapters.ShoppingItemsAdapter
import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_new_list.*

@AndroidEntryPoint
class NewListFragment: Fragment(R.layout.fragment_new_list) {

    private val viewModel: ShoppingViewModel by activityViewModels()
    private val args by navArgs<NewListFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        amountPicker.apply {
            minValue = 1
            maxValue = 100
            wrapSelectorWheel = true
        }

        list_name_TextView.text = args.currentList.name

        addItemButton.setOnClickListener {
            val name = itemNameEditText.text.toString()
            if (name.isEmpty()){
                Toast.makeText(context, "Enter item name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val amount = amountPicker.value
            val currentListId = args.currentList.id
            val newItem = ShoppingItem(currentListId!!, name, amount, 0)
            viewModel.upsertItem(newItem)
            itemNameEditText.setText("")

        }

        if (args.currentList.archive == 0){
            unarchiveButton.visibility = GONE
        } else{
            archiveButton.visibility = GONE
            addItemButton.visibility = GONE
            amountPicker.visibility = GONE
            itemNameEditText.visibility = GONE
        }

        val adapter = ShoppingItemsAdapter(listOf(), viewModel, args.currentList.archive)
        itemsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemsRecyclerView.adapter = adapter

        viewModel.getItemsFromCurrentList(args.currentList.id!!).observe(viewLifecycleOwner, Observer{
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        archiveButton.setOnClickListener {
            val action = NewListFragmentDirections.actionNewListFragmentToActiveShoppingListsFragment()
            findNavController().navigate(action)
            args.currentList.archive = 1
            viewModel.upsert(args.currentList)
        }

        unarchiveButton.setOnClickListener {
            val action = NewListFragmentDirections.actionNewListFragmentToActiveShoppingListsFragment()
            findNavController().navigate(action)
            args.currentList.archive = 0
            viewModel.upsert(args.currentList)
        }

        deleteListButton.setOnClickListener {
            val action = NewListFragmentDirections.actionNewListFragmentToActiveShoppingListsFragment()
            findNavController().navigate(action)
            viewModel.delete(args.currentList)
        }

    }

}