package com.krolikowski.shoppinglistapplication.data.repositories

import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.data.db.ShoppingItemsDao
import com.krolikowski.shoppinglistapplication.data.db.ShoppingListsDao
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

@Module
@InstallIn(ActivityRetainedComponent::class)
class ShoppingRepository @Inject constructor(
    val listDao: ShoppingListsDao,
    val itemDao: ShoppingItemsDao
        ) {

    suspend fun upsert(list: ShoppingList) = listDao.upsert(list)
    suspend fun delete(list: ShoppingList) = listDao.delete(list)
    fun getAllShoppingLists() = listDao.getAllShoppingLists()
    fun getActiveShoppingLists() = listDao.getActiveShoppingLists()
    fun getArchiveShoppingLists() = listDao.getArchiveShoppingLists()

    suspend fun upsertItem(item: ShoppingItem) = itemDao.upsertItem(item)
    suspend fun deleteItem(item: ShoppingItem) = itemDao.deleteItem(item)
    fun getAllShoppingItems() = itemDao.getAllShoppingItems()
    fun getItemsFromCurrentList(currentListId: Int) = itemDao.getItemsFromCurrentList(currentListId)


}