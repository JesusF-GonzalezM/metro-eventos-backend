package org.unimet.eventManager.dto


data class UserDTO(
    var name: String,
    var lastName: String,
    val email: String,
    var password: String,
    val role: String,
    var gender: String?,
    var birthDate: String?,
    var profilePhoto: String?,
    var subscribedEvents: MutableList<String> = mutableListOf()
)

data class AuthenticationRequest(
    val email: String,
    val password: String
)

data class JwtResponse(val token: String)
