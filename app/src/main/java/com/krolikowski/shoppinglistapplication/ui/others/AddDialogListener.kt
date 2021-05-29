package com.krolikowski.shoppinglistapplication.ui.others

import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList

interface AddDialogListener {
    fun onAddButtonClicked(list: ShoppingList)
}