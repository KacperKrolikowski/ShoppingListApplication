package com.krolikowski.shoppinglistapplication.ui.others

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.krolikowski.shoppinglistapplication.R
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingList
import kotlinx.android.synthetic.main.dialog_add_new_list.*

class AddNewShoppingListDialog(context: Context, var addDialogListener: AddDialogListener): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_new_list)

        tvAdd.setOnClickListener {
            val name = listNameEditText.text.toString()

            if (name.isEmpty()){
                Toast.makeText(context, "Enter list name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val list = ShoppingList(name, 0)
            addDialogListener.onAddButtonClicked(list)
            dismiss()
        }

        tvCancel.setOnClickListener {
            cancel()
        }
    }

}