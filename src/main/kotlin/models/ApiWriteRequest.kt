package dev.naijun.talkapi.models

import dev.naijun.talkapi.utils.generateMessageId
import kotlinx.serialization.Serializable

@Serializable
data class ApiWriteRequest(
    val chatId: Long,
    val type: Int = 1,
    val message: String,
    val attachment: String = "{}",
    val msgId: Long = generateMessageId()
)
