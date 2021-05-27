package com.krolikowski.shoppinglistapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList

@Database(
    entities = [ShoppingList::class, ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {

    abstract fun getShoppingListsDao(): ShoppingListsDao
    abstract fun getShoppingItemsDao(): ShoppingItemsDao

    companion object{
        @Volatile
        private var instance: ShoppingDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ShoppingDatabase::class.java,
            "ShoppingDB.db")
                .build()
    }
}