/*
 * Copyright (c) 2020-2021. kokoro-aya. All right reserved.
 * Amatsukaze - A Playground Server implemented with ANTLR or Kotlin DSL
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
import org.ironica.amatsukaze.bridge.AmatsukazeBridge
import org.ironica.amatsukaze.bridge.IncomingData
import org.ironica.amatsukaze.payloads.*
import java.lang.Exception

fun Route.getPlaygroundRoute(args: Pair<Boolean, Boolean>) {
    route("/paidiki-xara") {
        post {
            val data = call.receive<IncomingData>()
            val debug = args.first
            val stdout = args.second
            val playgroundInterface = AmatsukazeBridge(
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
                stdout = stdout
            )
            try {
                playgroundInterface.start()
                val moves = payloadStorage
                call.respond(NormalMessage(Status.OK, moves.get(), statusStorage.get()))
                println("Proceeded succesfully a request.")
            } catch (e: Exception) {
                call.respond(ErrorMessage(Status.ERROR, e.message ?: ""))
                println("Encountered an error.")
            }
        }
    }
}

fun Application.registerPlaygroundRoutes(args: Pair<Boolean, Boolean>) {
    routing {
        getPlaygroundRoute(args)
    }
}