package com.krolikowski.shoppinglistapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.ui.MainActivity
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel

class activeShoppingListsFragment: Fragment(R.layout.fragment_active_shopping_lists) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }





}