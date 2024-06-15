package com.nqmgaming.test.data.dto

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("ph31902_id")
    val ph31902Id: String? = null,
    @SerializedName("ph31902_name")
    val ph31902Name: String? = null,
    @SerializedName("ph31902_price")
    val ph31902Price: Double? = null,
    @SerializedName("ph31902_model")
    val ph31902Model: String? = null,
    @SerializedName("ph31902_status")
    val ph31902Status: Boolean? = null,
)
