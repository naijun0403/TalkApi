package dev.naijun.talkapi.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorModel(
    val message: String,
    val status: Int,
)
