package org.unimet.eventManager.repositories

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.unimet.eventManager.models.Event

interface EventRepository : MongoRepository<Event, String> {

    @Query("{id: '?0'}")
    fun findEventById(eventId: String): Event?

    fun findAllByOrderByDateAsc(pageable: PageRequest): Page<Event>

    fun findAllById(ids: MutableList<String>): List<Event>
}