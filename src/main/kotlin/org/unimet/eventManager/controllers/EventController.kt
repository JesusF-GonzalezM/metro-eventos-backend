package org.unimet.eventManager.controllers

import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.EventDTO
import org.unimet.eventManager.models.Event
import org.unimet.eventManager.repositories.EventRepository
//import org.unimet.eventManager.services.EventService


@RestController
@RequestMapping("/events")
class EventController (
    val eventRepository: EventRepository,
    //val eventService: EventService
) {

    @CrossOrigin(origins = ["*"])
    @PostMapping
    fun createEvent(
        @RequestBody eventDTO: EventDTO,
    ): Event {
        val event = Event(id=eventDTO.id, path=eventDTO.path, title=eventDTO.title, date=eventDTO.date, author=eventDTO.author, description=eventDTO.description,
            entryType=eventDTO.entryType, place=eventDTO.place, label=eventDTO.label)

        return eventRepository.save(event)
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping
    fun getAllEvents(): List<Event> {
        return eventRepository.findAll()
    }

//    @CrossOrigin(origins = ["*"])
//    @GetMapping()
//    fun getEvents(
//        @RequestParam(defaultValue = "0") page: Int,
//        @RequestParam(defaultValue = "10") size: Int
//    ): Page<Event> {
//        val events = eventService.getPaginatedEvents(page, size)
//        return events
//    }



}