package com.example.droidchat.commom.data.network.model

import com.example.droidchat.commom.domain.model.ImageDomain
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    val id: Int,
    val name: String,
    val type: String,
    val url: String
)

fun ImageResponse.toImageDomain(): ImageDomain {
    return ImageDomain(
        id = id,
        name = name,
        type = type,
        url = url,
    )
}
