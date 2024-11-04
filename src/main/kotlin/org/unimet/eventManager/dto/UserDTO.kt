package org.unimet.eventManager.dto
import org.springframework.data.mongodb.core.mapping.Document


data class UserDTO(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String
)

data class JwtResponse(val token: String)
