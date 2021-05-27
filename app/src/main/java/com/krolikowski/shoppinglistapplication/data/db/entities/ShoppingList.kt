package com.krolikowski.shoppinglistapplication.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shopping_lists")
data class ShoppingList(
    @ColumnInfo(name = "list_name")
    var name: String,
    @ColumnInfo(name = "list_archive")
    var archive: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
