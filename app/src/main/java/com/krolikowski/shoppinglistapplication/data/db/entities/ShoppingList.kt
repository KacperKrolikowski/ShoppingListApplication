package com.krolikowski.shoppinglistapplication.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "shopping_lists")
data class ShoppingList(
    @ColumnInfo(name = "list_name", defaultValue = "New list")
    var name: String,
    @ColumnInfo(name = "list_archive", defaultValue = "0")
    var archive: Int
): Parcelable{
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
