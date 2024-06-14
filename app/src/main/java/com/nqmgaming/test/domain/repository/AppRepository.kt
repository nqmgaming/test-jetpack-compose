package com.nqmgaming.test.domain.repository

import com.nqmgaming.test.domain.model.Item

interface AppRepository {
    suspend fun getAllItems(): List<Item>
    suspend fun getItemById(id: String): Item
    suspend fun insertItem(item: Item): Item
    suspend fun deleteItem(id: String)
}