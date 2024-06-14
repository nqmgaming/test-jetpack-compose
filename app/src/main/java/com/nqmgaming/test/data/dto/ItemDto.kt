package com.nqmgaming.test.data.dto

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("status")
    val status: Boolean? = null,
)
