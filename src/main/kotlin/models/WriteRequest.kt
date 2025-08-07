package dev.naijun.talkapi.models

import dev.naijun.talkapi.utils.generateMessageId
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

@Serializable
data class WriteRequest(
    val chatId: Long,
    val type: Int = 1,
    val message: String,
    val attachment: JsonObject = buildJsonObject {},
    val msgId: Long = generateMessageId()
)

fun WriteRequest.toApiModel(): ApiWriteRequest = ApiWriteRequest(
    chatId = this.chatId,
    type = this.type,
    message = this.message,
    attachment = Json.encodeToString(JsonObject.serializer(), JsonObject(this.attachment)),
    msgId = this.msgId
)