package com.krolikowski.shoppinglistapplication.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_list_id")
    var listId: Int,
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int,
    @ColumnInfo(name = "item_state")
    var state: Int,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
