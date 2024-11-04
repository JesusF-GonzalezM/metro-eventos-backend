package org.unimet.eventManager.services

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.unimet.eventManager.models.Event
import org.unimet.eventManager.repositories.EventRepository

@Service
class EventService(private val eventRepository: EventRepository) {

    fun getPaginatedEvents(page: Int, size: Int): Page<Event> {
        val pageable = PageRequest.of(page, size)
        return eventRepository.findAllByOrderByDateAsc(pageable)
    }
}