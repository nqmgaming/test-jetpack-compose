package com.nqmgaming.test.data.mapper

import com.nqmgaming.test.data.dto.ItemDto
import com.nqmgaming.test.domain.model.Item

fun ItemDto.toDomain() = Item(
    id = id,
    albumId = albumId,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)

fun Item.toDto() = ItemDto(
    id = id,
    albumId = albumId,
    title = title,
    url = url,
    thumbnailUrl = thumbnailUrl
)