package dev.naijun.talkapi.plugins

import dev.naijun.talkapi.models.ErrorModel
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.plugins.ContentTransformationException
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.application.log.error("Unhandled exception: ${cause.message}")
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorModel(
                    status = HttpStatusCode.InternalServerError.value,
                    message = "An error occurred: ${cause.message}",
                ),
            )
        }

        exception<ContentTransformationException> { call, cause ->
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorModel(
                    status = HttpStatusCode.BadRequest.value,
                    message = "Invalid request format: ${cause.message}",
                ),
            )
        }

        status(HttpStatusCode.NotFound) {
            call.respond(
                HttpStatusCode.NotFound,
                ErrorModel(
                    status = HttpStatusCode.NotFound.value,
                    message = "Resource not found",
                ),
            )
        }

        status(HttpStatusCode.Unauthorized) {
            call.respond(
                HttpStatusCode.Unauthorized,
                ErrorModel(
                    status = HttpStatusCode.Unauthorized.value,
                    message = "Unauthorized access",
                ),
            )
        }

        status(HttpStatusCode.Forbidden) {
            call.respond(
                HttpStatusCode.Forbidden,
                ErrorModel(
                    status = HttpStatusCode.Forbidden.value,
                    message = "Forbidden access",
                ),
            )
        }

        status(HttpStatusCode.BadRequest) {
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorModel(
                    status = HttpStatusCode.BadRequest.value,
                    message = "Bad request",
                ),
            )
        }

        status(HttpStatusCode.MethodNotAllowed) {
            call.respond(
                HttpStatusCode.MethodNotAllowed,
                ErrorModel(
                    status = HttpStatusCode.MethodNotAllowed.value,
                    message = "Method not allowed",
                ),
            )
        }

        status(HttpStatusCode.UnsupportedMediaType) {
            call.respond(
                HttpStatusCode.UnsupportedMediaType,
                ErrorModel(
                    status = HttpStatusCode.UnsupportedMediaType.value,
                    message = "Unsupported media type",
                ),
            )
        }
    }
}