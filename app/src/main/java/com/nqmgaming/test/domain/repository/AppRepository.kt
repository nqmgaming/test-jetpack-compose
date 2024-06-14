package com.nqmgaming.test.domain.repository

import com.nqmgaming.test.domain.model.Item

interface AppRepository {
    suspend fun getAllItems(): List<Item>
    suspend fun getItemById(id: Int): Item
}