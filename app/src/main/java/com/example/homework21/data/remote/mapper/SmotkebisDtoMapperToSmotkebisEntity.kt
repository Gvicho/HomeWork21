package com.example.homework21.data.remote.mapper

import com.example.homework21.data.local.model.ShmotkebisEntity
import com.example.homework21.data.remote.model.ShmotkebisDto

fun ShmotkebisDto.toLocal():ShmotkebisEntity{
    return ShmotkebisEntity(
        id = id,
        cover = cover,
        price = price,
        favorite = favorite,
        title = title
    )
}