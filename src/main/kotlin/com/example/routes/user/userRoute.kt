package com.example.routes.user

import com.example.domain.usecases.LoginUseCase
import com.example.domain.usecases.RegisterUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoute() {
    val registerUseCase by inject<RegisterUseCase>()
    val loginUseCase by inject<LoginUseCase>()
    route(path = "/user") {
            post(path = "/register") {
                val userFormParameters = call.receive<CreateUserParams>()
                val registerResult = registerUseCase(
                    name = userFormParameters.name,
                    email = userFormParameters.email,
                    password = userFormParameters.password
                )
                call.respond(message = registerResult, status = registerResult.statuesCode)
        }
        post(path = "/login") {
            val userFormParameters = call.receive<UserLoginParams>()
            val loginResult = loginUseCase(email = userFormParameters.email, password = userFormParameters.password)
            call.respond(message = loginResult, status = loginResult.statuesCode)
        }
    }
}