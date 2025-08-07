package dev.naijun.talkapi

import dev.naijun.talkapi.plugins.*
import dev.naijun.talkapi.routes.api.apiModules
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureFrameworks()
    configureStatusPages()
    configureRouting()

    apiModules()
}
