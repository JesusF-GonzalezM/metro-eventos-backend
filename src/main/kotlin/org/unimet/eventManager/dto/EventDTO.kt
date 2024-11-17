package org.unimet.eventManager.dto


data class EventDTO(
    var id: String? = null,
    var title: String,
    var path: String,
    var date: String,
    var place: String,
    var author: String,
    var entryType: String,
    var description: String? = null,
    var label: List<String>
)
