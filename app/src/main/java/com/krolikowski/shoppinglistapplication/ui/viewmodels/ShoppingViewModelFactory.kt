package com.krolikowski.shoppinglistapplication.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krolikowski.shoppinglistapplication.data.repositories.ShoppingRepository


class ShoppingViewModelFactory(
    val app: Application,
    val repository: ShoppingRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(app, repository) as T
    }

}