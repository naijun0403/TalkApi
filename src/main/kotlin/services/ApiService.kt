package dev.naijun.talkapi.services

import dev.naijun.talkapi.models.WriteRequest
import dev.naijun.talkapi.models.toApiModel
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiService {

    private val url = System.getenv("API_URL") ?: throw RuntimeException("Talk API URL not set")
    private val client = HttpClient(OkHttp) {
        engine {
            config {
                followRedirects(true)
            }
        }

        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun writeRequest(authorization: String, request: WriteRequest): HttpResponse = withContext(Dispatchers.IO) {
        val (accessToken, deviceUUID) = authorization.split("-")

        return@withContext client.post(url) {
            headers {
                append(HttpHeaders.Authorization, accessToken)
                append("Duuid", deviceUUID)
                append(HttpHeaders.UserAgent, "okhttp/4.12.0")
                append(HttpHeaders.Accept, "application/json")
            }
            contentType(ContentType.Application.Json)
            setBody(request.toApiModel())
        }
    }

}