package com.krolikowski.shoppinglistapplication.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_list_id")
    var listId: Int,
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int,
    @ColumnInfo(name = "item_details")
    var details: String,
    @ColumnInfo(name = "item_archived")
    var archived: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
