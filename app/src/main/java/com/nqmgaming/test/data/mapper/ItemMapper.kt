package com.nqmgaming.test.data.mapper

import com.nqmgaming.test.data.dto.ItemDto
import com.nqmgaming.test.domain.model.Item

fun ItemDto.toDomain() = Item(
    id = id,
    name = name,
    price = price,
    description = description,
    status = status
)

fun Item.toDto() = ItemDto(
    id = id,
    name = name,
    price = price,
    description = description,
    status = status
)