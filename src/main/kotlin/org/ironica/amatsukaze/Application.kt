package org.ironica.amatsukaze

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.json
import routes.registerPlaygroundRoutes


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(CORS) {
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        header(HttpHeaders.AccessControlAllowOrigin)
        header(HttpHeaders.ContentType)
        anyHost()
    }
    install(ContentNegotiation) {
        json()
    }
    registerPlaygroundRoutes()
}