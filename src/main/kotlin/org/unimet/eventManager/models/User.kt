package org.unimet.eventManager.models

import org.springframework.data.mongodb.core.mapping.Document

@Document("User")
data class User(val id: String, val email: String, var password: String)
