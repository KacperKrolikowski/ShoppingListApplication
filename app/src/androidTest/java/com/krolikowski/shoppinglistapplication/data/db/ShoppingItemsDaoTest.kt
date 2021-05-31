package com.krolikowski.shoppinglistapplication.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.krolikowski.shoppinglistapplication.data.db.entities.ShoppingItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingItemsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingDatabase
    private lateinit var dao: ShoppingItemsDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getShoppingItemsDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun upsertItem() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "name", 5, 0, id = 1)
        dao.upsertItem(shoppingItem)
        val allShoppingItems = dao.getAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteItem() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "name", 5, 1, id = 1)

        dao.upsertItem(shoppingItem)
        dao.deleteItem(shoppingItem)

        val allShoppingItems = dao.getAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun getItemsFromCurrentList() = runBlockingTest {
        val shoppingItem = ShoppingItem(1, "name", 1, 1, id = 1)
        val shoppingItem2 = ShoppingItem(1, "name2", 2, 1, id = 2)
        val shoppingItem3 = ShoppingItem(2, "name3", 3, 1, id = 3)
        val shoppingItem4 = ShoppingItem(3, "name4", 4, 1, id = 4)

        dao.upsertItem(shoppingItem)
        dao.upsertItem(shoppingItem2)
        dao.upsertItem(shoppingItem3)
        dao.upsertItem(shoppingItem4)

        val currentListShoppingItems = dao.getItemsFromCurrentList(1).getOrAwaitValue()

        assertThat(currentListShoppingItems).contains(shoppingItem)
        assertThat(currentListShoppingItems).contains(shoppingItem2)
        assertThat(currentListShoppingItems).doesNotContain(shoppingItem3)
        assertThat(currentListShoppingItems).doesNotContain(shoppingItem4)

    }

}