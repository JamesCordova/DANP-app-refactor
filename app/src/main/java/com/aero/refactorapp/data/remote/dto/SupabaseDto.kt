package com.aero.refactorapp.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    @SerialName("created_at")
    val createdAt: String? = null,
    val name: String,
    val description: String? = "",
    val price: Double = 0.0,
    @SerialName("category")
    val categoryId: Int,
    @SerialName("image_url")
    val imageUrl: String? = ""
)

@Serializable
data class CategoryDto(
    val id: Int,
    @SerialName("created_at")
    val createdAt: String? = null,
    val name: String,
    val description: String? = null
)
