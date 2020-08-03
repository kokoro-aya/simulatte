package routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import org.ironica.playground.*
import java.lang.Exception

fun Route.getPlaygroundRoute() {
    route("/playground") {
        post {
            val code = call.receive<Code>()
            payloadStorage.clear()
            val playgroundInterface = PlaygroundInterface(code.value)
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