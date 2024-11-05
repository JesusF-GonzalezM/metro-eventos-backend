package org.unimet.eventManager.dto


data class EventDTO(
    var id: String? = null,
    val title: String,
    val path: String? = null,
    val date: String,
    val place: String,
    val author: String,
    val entryType: String,
    val description: String? = null,
    val linkCompra: String? = null
)
