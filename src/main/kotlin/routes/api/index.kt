package dev.naijun.talkapi.routes.api

import dev.naijun.talkapi.services.ApiService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.apiModules() {
    val apiService by inject<ApiService>()

    routing {
        routeApiV1Send(apiService)
    }
}