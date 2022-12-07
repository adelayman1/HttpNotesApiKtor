package com.example.routes.note

import com.example.domain.usecases.*
import com.example.routes.note.requestsModels.AddNoteParams
import com.example.routes.note.requestsModels.UpdateNoteParams
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.noteRoute() {
    val addNoteUseCase by inject<AddNoteUseCase>()
    val updateNoteDetailsUseCase by inject<UpdateNoteDetailsUseCase>()
    val getAllUserNotesUseCase by inject<GetAllUserNotesUseCase>()
    val getNoteDetailsUseCase by inject<GetNoteDetailsUseCase>()
    val searchNoteUseCase by inject<SearchNoteUseCase>()
    val deleteNoteUseCase by inject<DeleteNoteUseCase>()
    authenticate("jwt_auth") {
        route(path = "/notes") {
            get {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                val limit = call.request.queryParameters["limit"]?.toInt()
                val offset = call.request.queryParameters["offset"]?.toInt()
                val getNotesResult = getAllUserNotesUseCase(email = email, limit, offset)

                call.respond(message = getNotesResult, status = getNotesResult.statuesCode)
            }
            get("/{note_id}") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                val getNoteDetailsResult = getNoteDetailsUseCase(email = email, noteID = call.parameters["note_id"])

                call.respond(message = getNoteDetailsResult, status = getNoteDetailsResult.statuesCode)
            }
            post {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                val addNoteBodyParameters = call.receive<AddNoteParams>()
                val addNoteResult = addNoteUseCase(
                    email = email,
                    title = addNoteBodyParameters.title,
                    subtitle = addNoteBodyParameters.subtitle,
                    description = addNoteBodyParameters.description,
                    image = addNoteBodyParameters.image,
                    webLink = addNoteBodyParameters.webLink,
                    color = addNoteBodyParameters.color,
                )

                call.respond(message = addNoteResult, status = addNoteResult.statuesCode)
            }
            delete("/{note_id}") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                val deleteNoteResult = deleteNoteUseCase(
                    email = email, noteID = call.parameters["note_id"]
                )

                call.respond(message = deleteNoteResult, status = deleteNoteResult.statuesCode)
            }
            get("/search") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                val limit = call.request.queryParameters["limit"]?.toInt()
                val offset = call.request.queryParameters["offset"]?.toInt()
                val getSearchResult = searchNoteUseCase(
                    email = email, searchWord = call.parameters["search_word"],
                    limit,
                    offset
                )
                call.respond(message = getSearchResult, status = getSearchResult.statuesCode)
            }
            patch("/{note_id}") {
                val principal = call.principal<JWTPrincipal>()
                val email = principal!!.payload.getClaim("email").asString()
                val updateNoteBodyParameters = call.receive<UpdateNoteParams>()
                val updateNoteResult = updateNoteDetailsUseCase(
                    email = email,
                    noteID = call.parameters["note_id"],
                    title = updateNoteBodyParameters.title,
                    subtitle = updateNoteBodyParameters.subtitle,
                    description = updateNoteBodyParameters.description,
                    webLink = updateNoteBodyParameters.webLink,
                    image = updateNoteBodyParameters.image,
                    color = updateNoteBodyParameters.color,
                )

                call.respond(message = updateNoteResult, status = updateNoteResult.statuesCode)
            }
        }
    }
}