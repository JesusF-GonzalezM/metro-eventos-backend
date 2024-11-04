package org.unimet.eventManager.dto


data class UserDTO(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String
)

data class AuthenticationRequest(
    val email: String,
    val password: String
)

data class JwtResponse(val token: String)
