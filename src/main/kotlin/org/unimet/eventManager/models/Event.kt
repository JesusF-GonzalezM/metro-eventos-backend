package org.unimet.eventManager.models

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId

@Document("Event")
data class Event(
    @MongoId(targetType = FieldType.STRING)
    var id: String? = null,
    val title: String,
    val date: String,
    val place: String,
    val author: String,
    val entryType: String,
    val description: String,
    val location: String,
    val linkCompra: String
)
