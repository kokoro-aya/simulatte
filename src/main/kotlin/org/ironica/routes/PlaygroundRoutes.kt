/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.ironica.routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import kotlinx.coroutines.async
import org.ironica.simulatte.bridge.SimulatteBridge
import org.ironica.simulatte.bridge.IncomingData
import org.ironica.simulatte.payloads.*
import org.ironica.simulatte.playground.GameStatus
import kotlin.Exception

fun Route.getPlaygroundRoute(vararg args: String?) {
    route("/simulatte") {
        post {
            val data = call.receive<IncomingData>()
            val debug = args.any { it == "debug" }
            val stdout = args.any { it == "stdout" }
            val output = args.any { it == "output" }
            val playgroundInterface = SimulatteBridge(
                data.type,
                data.code,
                data.grid,
                data.gems,
                data.beepers,
                data.switches,
                data.portals,
                data.monsters,
                data.locks,
                data.stairs,
                data.platforms,
                data.players,
                data.gamingCondition,
                data.userCollision,
                debug = debug,
                stdout = stdout,
                output = output,
            )
            try {
                val p = async { playgroundInterface.start() } // usage of async-await to not to block the main thread
                val resp = p.await() // might be insignificant to end user since the server thread is invisible to them

                // notice that resp returns a pair, which could contains another pair in its first element
                // we should switch on second element then the type of first element for each possible cases
                when (resp.second) {
                    Status.OK -> {
                        when (val rsf = resp.first) {
                            is Triple<*, *, *> -> {
                                call.respond(
                                    NormalMessage(
                                        resp.second,
                                        (rsf as Triple<List<Payload>, GameStatus, Int>).first,
                                        (rsf as Triple<List<Payload>, GameStatus, Int>).second,
                                        (rsf as Triple<List<Payload>, GameStatus, Int>).third,
                                    )
                                )
                            }
                            is String -> {
                                println("Route:: encountered some error \n$rsf\n")
                                call.respond(
                                    ErrorMessage(Status.ERROR, rsf)
                                )
                            }
                            else -> {
                                throw Exception("PlaygroundRoutes:: this is impossible")
                            }
                        }
                    }
                    Status.ERROR -> {
                        println("Route:: encountered some error\n${resp.first as String}\n")
                        call.respond(ErrorMessage(
                            resp.second, resp.first as String))
                    }
                    Status.INCOMPLETE -> {
                        println("Route:: The code is not complete.\n")
                        call.respond(ErrorMessage(
                            resp.second, "incomplete code"))
                    }
                }
                println("Proceeded succesfully a request.")
            } catch (e: Exception) {

                // Rarely will we encounter this: for internal errors
                call.respond(ErrorMessage(Status.ERROR, e.message ?: "PlaygroundRoutes:: Unknown internal error, please contact developer"))
                println("Encountered an error.")
                e.printStackTrace()
            }
        }
    }
}

fun Application.registerPlaygroundRoutes(vararg args: String?) {
    routing {
        getPlaygroundRoute(*args)
    }
}