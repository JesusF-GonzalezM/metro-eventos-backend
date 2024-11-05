package org.unimet.eventManager.controllers

import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.EventDTO
import org.unimet.eventManager.models.Event
import org.unimet.eventManager.repositories.EventRepository
import org.unimet.eventManager.services.EventService


@RestController
@RequestMapping("/events")
class EventController (
    val eventRepository: EventRepository,
    val eventService: EventService
) {

    @PostMapping
    fun createEvent(
        @RequestBody eventDTO: EventDTO,
    ): Event {
        val event = Event(id=eventDTO.id, path=eventDTO.path, title=eventDTO.title, date=eventDTO.date, author=eventDTO.author, description=eventDTO.description,
            entryType=eventDTO.entryType, location=eventDTO.location, place=eventDTO.place, linkCompra=eventDTO.linkCompra)

        return eventRepository.save(event)
    }

    @GetMapping()
    fun getEvents(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): Page<Event> {
        return eventService.getPaginatedEvents(page, size)
    }

}