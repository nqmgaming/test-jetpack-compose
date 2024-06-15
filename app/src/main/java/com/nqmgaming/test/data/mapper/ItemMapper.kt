package com.nqmgaming.test.data.mapper

import com.nqmgaming.test.data.dto.ItemDto
import com.nqmgaming.test.domain.model.Item

fun ItemDto.toDomain() = Item(
    ph31902Id = ph31902Id,
    ph31902Name = ph31902Name,
    ph31902Price = ph31902Price,
    ph31902Model = ph31902Model,
    ph31902Status = ph31902Status
)

fun Item.toDto() = ItemDto(
    ph31902Id = ph31902Id,
    ph31902Name = ph31902Name,
    ph31902Price = ph31902Price,
    ph31902Model = ph31902Model,
    ph31902Status = ph31902Status
)