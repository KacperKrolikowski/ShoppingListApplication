package com.krolikowski.shoppinglistapplication.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private var repository: ShoppingRepository
): ViewModel() {

    fun upsert(list: ShoppingList) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(list)
    }

    fun upsertItem(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsertItem(item)
    }

    fun delete(list: ShoppingList) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(list)
    }

    fun deleteItem(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteItem(item)
    }

    fun getAllShoppingLists() = repository.getAllShoppingLists()
    fun getActiveShoppingLists() = repository.getActiveShoppingLists()
    fun getArchiveShoppingLists() = repository.getArchiveShoppingLists()

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}