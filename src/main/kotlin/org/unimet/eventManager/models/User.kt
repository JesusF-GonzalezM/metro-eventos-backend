package org.unimet.eventManager.models

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId

@Document("User")
data class User(
    @MongoId(targetType = FieldType.STRING)
    var id: String? = null,
    val email: String,
    var password: String,
    var name: String,
    var lastName: String,
    val role: String,
    var gender: String?,
    var birthDate: String?,
    var profilePhoto: String?
)
