package com.krolikowski.shoppinglistapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList

@Dao
interface ShoppingListsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: ShoppingList)

    @Delete
    suspend fun delete(list: ShoppingList)

    @Query("SELECT * FROM shopping_lists")
    fun getAllShoppingLists(): LiveData<List<ShoppingList>>
}