package org.unimet.eventManager.dto


data class EventDTO(
    var id: String,
    val title: String,
    val date: String,
    val place: String,
    val author: String,
    val entryType: String,
    val description: String,
    val location: String,
    val linkCompra: String
)
