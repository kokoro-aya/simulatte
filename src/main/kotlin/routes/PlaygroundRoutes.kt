package routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import org.ironica.amatsukaze.*
import java.lang.Exception

fun Route.getPlaygroundRoute(args: Pair<Boolean, Boolean>) {
    route("/paidiki-xara") {
        post {
            val data = call.receive<Data>()
            payloadStorage.clear()
            val debug = args.first
            val stdout = args.second
            val playgroundInterface = AmatsukazeInterface(
                data.type,
                data.code,
                data.grid.map { it.toMutableList() }.toMutableList(),
                data.layout.let {
                    if (it.size == data.grid.size && it[0].size == data.grid[0].size) it.map { it.toMutableList() }.toMutableList()
                    else with (data.grid) {
                        MutableList(this.size) { MutableList(this[0].size) { Item.NONE } }
                    } },
                convertJsonToMiscLayout(data.colors, data.levels, data.type, data.grid.size to data.grid[0].size),
                data.portals,
                data.locks,
                data.stairs,
                convertJsonToPlayers(data.players),
                debug = debug,
                stdout = stdout
            )
            try {
                playgroundInterface.start()
                val moves = payloadStorage
                call.respond(NormalMessage(Status.OK, moves))
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