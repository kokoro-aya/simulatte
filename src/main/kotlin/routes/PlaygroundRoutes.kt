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
import org.ironica.playground.Code
import org.ironica.playground.PlaygroundInterface
import org.ironica.playground.payloadStorage

fun Route.getPlaygroundRoute() {
    route("/playground") {
        post {
            val code = call.receive<Code>()
            val playgroundInterface = PlaygroundInterface(code.code)
            playgroundInterface.start()
            val moves = payloadStorage
            call.respond(moves)
        }
    }
}

fun Application.registerPlaygroundRoutes() {
    routing {
        getPlaygroundRoute()
    }
}