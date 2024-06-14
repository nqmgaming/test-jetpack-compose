package com.nqmgaming.test.data.remote

import com.nqmgaming.test.data.dto.ItemDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppApi {

    @GET("items")
    suspend fun getItems(): List<ItemDto>

    @GET("items/{id}")
    suspend fun getItemById(@Path("id") id: String): ItemDto

    @POST("items")
    suspend fun createItem(
        @Body itemDto: ItemDto
    ): ItemDto

    @DELETE("items/{id}")
    suspend fun deleteItemById(@Path("id") id: String)
}