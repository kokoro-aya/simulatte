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

fun Route.getPlaygroundRoute() {
    route("/paidiki-xara") {
        post {
            val data = call.receive<Data>()
            payloadStorage.clear()
            val playgroundInterface = PlaygroundInterface(data.code, convertJsonToGrid(data.grid), convertJsonToLayout(data.layout))
            try {
                playgroundInterface.start()
                val moves = payloadStorage
                call.respond(NormalMessage(Status.OK, payloadStorage))
            } catch (e: Exception) {
                call.respond(ErrorMessage(Status.ERROR, e.message ?: ""))
            }
        }
    }
}

fun Application.registerPlaygroundRoutes() {
    routing {
        getPlaygroundRoute()
    }
}