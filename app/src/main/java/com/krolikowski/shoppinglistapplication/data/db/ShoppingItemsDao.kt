package com.krolikowski.shoppinglistapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem

@Dao
interface ShoppingItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: ShoppingItem)

    @Delete
    suspend fun deleteItem(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}