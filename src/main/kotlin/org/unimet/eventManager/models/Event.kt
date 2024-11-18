package org.unimet.eventManager.models

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId

@Document("Event")
data class Event(
    @MongoId(targetType = FieldType.STRING)
    var id: String? = null,
    var title: String,
    var path: String,
    var date: String,
    var place: String,
    var author: String,
    var entryType: String,
    var description: String?,
    var label: List<String>,
    var userEmail: String
)
