package com.example.data.utilities

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.utilities.UserJWTConfig.AUDIENCE
import com.example.data.utilities.UserJWTConfig.ISSUER
import com.example.data.utilities.UserJWTConfig.REFRESH_TOKEN_EXPIRE_DATE
import com.example.data.utilities.UserJWTConfig.SECRET
import java.util.*

object UserJWTConfig {
    val SECRET = System.getenv("notesAPi_secret") ?: "secretKey"
    const val ISSUER = "http://127.0.0.1:9090/"
    const val AUDIENCE = "http://127.0.0.1:9090/ntes"
    const val REALM = "Access to notes"
    const val REFRESH_TOKEN_EXPIRE_DATE = (12 * 30L * 24L * 60L * 60L * 1000L)
}
fun generateToken(email: String): String =
    JWT.create()
        .withAudience(AUDIENCE)
        .withIssuer(ISSUER)
        .withClaim("email",email)
        .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_DATE))
        .sign(Algorithm.HMAC256(SECRET))