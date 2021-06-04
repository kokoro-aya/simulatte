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
import java.util.*

const val version = "3.1.2"

/**
 * Main Entry of program, we launch Netty server from here
 * Notice how arguments are passed into the server
 */
fun main(args: Array<String>): Unit {
    if (args.any { it.lowercase(Locale.getDefault()) == "about" }) {
        println("""
            Simulatte :: A Playground Server implemented with Kotlin DSL
            > v$version
            > created by kokoro-aya@ironica.moe, all right reserved.
            
            > usage: simulatte [debug|stdout|output|about]
            > see Readme.md for more information.
            """.trimIndent())
        return
    }
    val debug = "-P:debug=${args.any { it.lowercase(Locale.getDefault()) == "debug" }}"
    val stdout = "-P:stdout=${args.any { it.lowercase(Locale.getDefault()) == "stdout" }}"
    val output = "-P:output=${args.any { it.lowercase(Locale.getDefault()) == "output" }}"
    io.ktor.server.netty.EngineMain.main(arrayOf(*args, debug, stdout, output))
}

fun Application.module(testing: Boolean = false) {
    val debug = environment.config.property("debug").getString().toBoolean()
    val stdout = environment.config.property("stdout").getString().toBoolean()
    val output = environment.config.property("output").getString().toBoolean()
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
    registerPlaygroundRoutes(Triple(debug, stdout, output))
}