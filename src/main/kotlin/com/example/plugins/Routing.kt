package com.example.plugins

import com.example.routes.note.noteRoute
import com.example.routes.user.userRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        userRoute()
        noteRoute()
    }
}
