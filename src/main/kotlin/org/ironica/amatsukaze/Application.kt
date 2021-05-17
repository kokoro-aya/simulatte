package org.ironica.amatsukaze

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.serialization.json
import routes.registerPlaygroundRoutes


fun main(args: Array<String>): Unit {
    val debug = "-P:debug=${args.any{ it.lowercase() == "debug" }}"
    val stdout = "-P:stdout=${args.any { it.lowercase() == "stdout" }}"
    io.ktor.server.netty.EngineMain.main(arrayOf(*args, debug, stdout))
}

fun Application.module(testing: Boolean = false) {
    val debug = environment.config.property("debug").getString().toBoolean()
    val stdout = environment.config.property("stdout").getString().toBoolean()
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
    registerPlaygroundRoutes(debug to stdout)
}