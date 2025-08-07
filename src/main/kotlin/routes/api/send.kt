package dev.naijun.talkapi.routes.api

import dev.naijun.talkapi.models.ErrorModel
import dev.naijun.talkapi.models.WriteRequest
import dev.naijun.talkapi.services.ApiService
import io.ktor.client.call.*
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.JsonObject

fun Routing.routeApiV1Send(apiService: ApiService) {
    post<WriteRequest>("/api/v1/send") { req ->
        val authorization = call.request.headers["Authorization"]

        if (authorization.isNullOrBlank() || !authorization.contains("-")) {
            call.respond(
                HttpStatusCode.Unauthorized,
                ErrorModel(
                    status = HttpStatusCode.Unauthorized.value,
                    message = "Authorization header is missing or invalid"
                )
            )
        }

        try {
            val response = apiService.writeRequest(authorization!!, req)
            call.respond(
                response.body<JsonObject>()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorModel(
                    status = HttpStatusCode.InternalServerError.value,
                    message = e.localizedMessage ?: "Internal Server Error"
                )
            )
        }
    }
}