package com.nqmgaming.test.data.repository

import android.util.Log
import com.nqmgaming.test.data.mapper.toDomain
import com.nqmgaming.test.data.mapper.toDto
import com.nqmgaming.test.data.remote.AppApi
import com.nqmgaming.test.domain.model.Item
import com.nqmgaming.test.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val appApi: AppApi
) : AppRepository {

    private val TAG = "AppRepositoryImpl"

    override suspend fun getAllItems(): List<Item> {
        return try {
            appApi.getItems().map { it.toDomain() }
        } catch (e: Exception) {
            Log.d(TAG, "getAllItems: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getItemById(id: String): Item {
        return try {
            appApi.getItemById(id).toDomain()
        } catch (e: Exception) {
            Log.d(TAG, "getItemById: ${e.message}")
            Item()
        }
    }

    override suspend fun insertItem(item: Item): Item {
        return try {
            appApi.createItem(item.toDto()).toDomain()
        } catch (e: Exception) {
            Log.d(TAG, "insertItem: ${e.message}")
            Item()
        }
    }

    override suspend fun deleteItem(id: String) {
        try {
            appApi.deleteItemById(id)
        } catch (e: Exception) {
            Log.d(TAG, "deleteItem: ${e.message}")
        }
    }
}