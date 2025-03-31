package com.example.droidchat.commom.domain.model

import com.example.droidchat.commom.data.network.model.ImageResponse
import kotlinx.serialization.Serializable

@Serializable
data class ImageDomain(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)

fun ImageDomain.toImageResponse(): ImageResponse {
    return ImageResponse(
        id = id,
        name = name,
        type = type,
        url = url
    )
}
