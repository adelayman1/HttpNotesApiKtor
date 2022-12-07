package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.utilities.UserJWTConfig.AUDIENCE
import com.example.data.utilities.UserJWTConfig.ISSUER
import com.example.data.utilities.UserJWTConfig.REALM
import com.example.data.utilities.UserJWTConfig.SECRET
import com.example.domain.models.BaseResponse
import com.example.domain.repositories.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject

fun Application.configureSecurity() {
    val usersRepo by inject<UserRepository>()
    authentication {
        jwt(name = "jwt_auth") {
            realm = REALM
            verifier(
                JWT
                    .require(Algorithm.HMAC256(SECRET))
                    .withAudience(AUDIENCE)
                    .withIssuer(ISSUER)
                    .build()
            )
            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                val isUserEmailExist = usersRepo.isEmailExist(email = email)
                if (isUserEmailExist) {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { _, _ ->
                call.respond(HttpStatusCode.Unauthorized, BaseResponse.ErrorResponse(message = "Token is not valid"))
            }
        }
    }
}
