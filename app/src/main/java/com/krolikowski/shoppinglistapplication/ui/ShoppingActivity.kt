package com.krolikowski.shoppinglistapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModelFactory
import kotlinx.android.synthetic.main.activity_shopping.*


class ShoppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())

        navHostFragment.findNavController()
            .addOnDestinationChangedListener{_, destination, _ ->
                when(destination.id){
                    R.id.activeShoppingListsFragment, R.id.archivedShoppingListsFragment ->
                        bottomNavigationView.visibility = View.VISIBLE
                }
            }
    }
}