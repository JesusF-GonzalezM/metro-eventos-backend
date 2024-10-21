package org.unimet.eventManager

import org.springframework.data.mongodb.core.mapping.Document

@Document("User")
data class User(val id: String, val email: String, val password: String)
