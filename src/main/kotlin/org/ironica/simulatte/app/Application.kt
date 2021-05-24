/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.simulatte.app

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