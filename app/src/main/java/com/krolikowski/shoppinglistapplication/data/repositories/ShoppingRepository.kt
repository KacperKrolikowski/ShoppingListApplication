package com.krolikowski.shoppinglistapplication.data.repositories

import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList

class ShoppingRepository (
    private val  db: ShoppingDatabase
        ) {

    suspend fun upsert(list: ShoppingList) = db.getShoppingListsDao().upsert(list)
    suspend fun delete(list: ShoppingList) = db.getShoppingListsDao().delete(list)
    fun getAllShoppingLists() = db.getShoppingListsDao().getAllShoppingLists()
    fun getActiveShoppingLists() = db.getShoppingListsDao().getActiveShoppingLists()
    fun getArchiveShoppingLists() = db.getShoppingListsDao().getArchiveShoppingLists()

    suspend fun upsertItem(item: ShoppingItem) = db.getShoppingItemsDao().upsertItem(item)
    suspend fun deleteItem(item: ShoppingItem) = db.getShoppingItemsDao().deleteItem(item)
    fun getAllShoppingItems() = db.getShoppingItemsDao().getAllShoppingItems()
    fun getItemsFromCurrentList(currentListId: Int) = db.getShoppingItemsDao().getItemsFromCurrentList(currentListId)


}