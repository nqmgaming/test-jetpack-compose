package com.nqmgaming.test.data.remote

import com.nqmgaming.test.data.dto.ItemDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AppApi {

    @GET("cars")
    suspend fun getItems(): List<ItemDto>

    @GET("cars/{id}")
    suspend fun getItemById(@Path("id") id: String): ItemDto

    @POST("items")
    suspend fun createItem(
        @Body itemDto: ItemDto
    ): ItemDto

    @PUT("cars/{id}")
    suspend fun updateItemById(
        @Path("id") id: String,
        @Body itemDto: ItemDto
    ): ItemDto

    @DELETE("cars/{id}")
    suspend fun deleteItemById(@Path("id") id: String)
}