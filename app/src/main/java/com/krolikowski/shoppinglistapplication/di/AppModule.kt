package com.krolikowski.shoppinglistapplication.di

import android.content.Context
import androidx.room.Room
import com.krolikowski.shoppinglistapplication.data.db.ShoppingDatabase
import com.krolikowski.shoppinglistapplication.others.Constants.SHOPPING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        ShoppingDatabase::class.java,
        SHOPPING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideShoppingListsDao(db: ShoppingDatabase) = db.getShoppingListsDao()

    @Singleton
    @Provides
    fun providesShoppingItemsDao(db: ShoppingDatabase) = db.getShoppingItemsDao()
}