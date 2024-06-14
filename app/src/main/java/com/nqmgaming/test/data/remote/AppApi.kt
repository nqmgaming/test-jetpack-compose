package com.nqmgaming.test.data.remote

import com.nqmgaming.test.data.dto.ItemDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AppApi {

    @GET("photos")
    suspend fun getItems(): List<ItemDto>

    @GET("photos/{id}")
    suspend fun getItemById(@Path("id") id: Int): ItemDto
}