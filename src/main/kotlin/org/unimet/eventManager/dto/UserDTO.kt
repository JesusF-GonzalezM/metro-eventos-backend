package org.unimet.eventManager.dto
import org.springframework.data.mongodb.core.mapping.Document

@Document("UserDTO")
data class UserDTO(
    val email: String,
    val password: String
)
