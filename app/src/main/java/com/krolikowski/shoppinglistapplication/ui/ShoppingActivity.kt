package com.krolikowski.shoppinglistapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import com.krolikowski.shoppinglistapplication.ui.others.AddDialogListener
import com.krolikowski.shoppinglistapplication.ui.others.AddNewShoppingListDialog
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ListsViewModel
import com.krolikowski.shoppinglistapplication.ui.viewmodels.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_shopping.*

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {

    private val viewModel: ShoppingViewModel by viewModels()
    private val listsViewModel: ListsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        bottomNavigationView.setupWithNavController(navHostFragment.findNavController())
        bottomNavigationView.menu.getItem(1).isEnabled = false
        bottomNavigationView.background = null
        navHostFragment.findNavController()
            .addOnDestinationChangedListener{_, destination, _ ->
                when(destination.id){
                    R.id.activeShoppingListsFragment, R.id.archivedShoppingListsFragment ->{
                        bottomNavigationView.visibility = View.VISIBLE
                        addNewList.visibility = View.VISIBLE
                    }
                    else -> {
                        addNewList.visibility = View.INVISIBLE

                    }
                }
            }

        addNewList.setOnClickListener {
            AddNewShoppingListDialog(this,
                object : AddDialogListener {
                    override fun onAddButtonClicked(list: ShoppingList) {
                        viewModel.upsert(list)
                    }
                }).show()

        }

    }
}