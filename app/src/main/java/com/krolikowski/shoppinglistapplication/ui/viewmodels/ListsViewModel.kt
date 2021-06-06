package com.krolikowski.shoppinglistapplication.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListsViewModel @ViewModelInject constructor(
    private var repository: ShoppingRepository
): ViewModel(){

    fun upsert(list: ShoppingList) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(list)
    }

    fun delete(list: ShoppingList) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(list)
    }

    fun getAllShoppingLists() = repository.getAllShoppingLists()
    fun getActiveShoppingLists() = repository.getActiveShoppingLists()
    fun getArchiveShoppingLists() = repository.getArchiveShoppingLists()
}