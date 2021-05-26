/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Simulatte - A Playground Server implemented with Kotlin DSL
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.
 *
 */

package routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import org.ironica.simulatte.bridge.SimulatteBridge
import org.ironica.simulatte.bridge.IncomingData
import org.ironica.simulatte.payloads.*
import org.ironica.simulatte.playground.GameStatus
import kotlin.Exception

fun Route.getPlaygroundRoute(args: Triple<Boolean, Boolean, Boolean>) {
    route("/simulatte") {
        post {
            val data = call.receive<IncomingData>()
            val debug = args.first
            val stdout = args.second
            val output = args.third
            val playgroundInterface = SimulatteBridge(
                data.type,
                data.code,
                data.grid.map { it.toMutableList() }.toMutableList(),
                data.layout.map { it.toMutableList() }.toMutableList(),
                data.colors,
                data.levels,
                data.biomes,
                data.portals,
                data.locks,
                data.stairs,
                data.platforms,
                data.players,
                debug = debug,
                stdout = stdout,
                output = output,
            )
            try {
                val resp = playgroundInterface.start()
                when (resp.second) {
                    Status.OK -> {
                        when (val rsf = resp.first) {
                            is Pair<*, *> -> {
                                call.respond(
                                    NormalMessage(
                                        resp.second,
                                        (rsf as Pair<List<Payload>, GameStatus>).first,
                                        (rsf as Pair<List<Payload>, GameStatus>).second
                                    )
                                )
                            }
                            is String -> {
                                call.respond(
                                    ErrorMessage(resp.second, rsf)
                                )
                            }
                            else -> {
                                throw Exception("PlaygroundRoutes:: this is impossible")
                            }
                        }
                    }
                    Status.ERROR -> call.respond(ErrorMessage(
                        resp.second, resp.first as String))
                    Status.INCOMPLETE -> call.respond(ErrorMessage(
                        resp.second, resp.first as String))
                }
                println("Proceeded succesfully a request.")
            } catch (e: Exception) {
                call.respond(ErrorMessage(Status.ERROR, e.message ?: ""))
                println("Encountered an error.")
                e.printStackTrace()
            }
        }
    }
}

fun Application.registerPlaygroundRoutes(args: Triple<Boolean, Boolean, Boolean>) {
    routing {
        getPlaygroundRoute(args)
    }
}