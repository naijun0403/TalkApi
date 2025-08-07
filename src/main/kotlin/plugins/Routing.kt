package dev.naijun.talkapi.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)
    install(AutoHeadResponse)
    routing {
        get("/") {
            call.respondRedirect("/swagger")
        }
    }
}
