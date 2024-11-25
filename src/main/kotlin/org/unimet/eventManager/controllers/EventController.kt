package org.unimet.eventManager.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.EventDTO
import org.unimet.eventManager.models.Event
import org.unimet.eventManager.repositories.EventRepository

@RestController
@RequestMapping("/events")
class EventController (
    val eventRepository: EventRepository
) {

    @CrossOrigin(origins = ["*"])
    @PostMapping
    fun createEvent(
        @RequestBody eventDTO: EventDTO,
    ): Event {
        val event = Event(id=eventDTO.id, path=eventDTO.path, title=eventDTO.title, date=eventDTO.date, author=eventDTO.author, description=eventDTO.description,
            entryType=eventDTO.entryType, place=eventDTO.place, label=eventDTO.label, userEmail=eventDTO.userEmail)

        return eventRepository.save(event)
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping
    fun getAllEvents(): List<Event> {
        return eventRepository.findAll()
    }

    @CrossOrigin(origins = ["*"])
    @PutMapping("/{id}")
    fun updateEvent(
        @PathVariable id: String,
        @RequestBody eventDTO: EventDTO
    ): ResponseEntity<Any> {
        return try {
            val existingEvent = eventRepository.findById(id).orElseThrow {
                RuntimeException("Event with id $id not found")
            }

            existingEvent.apply {
                path = eventDTO.path
                title = eventDTO.title
                date = eventDTO.date
                author = eventDTO.author
                description = eventDTO.description
                entryType = eventDTO.entryType
                place = eventDTO.place
                label = eventDTO.label
            }

            eventRepository.save(existingEvent)
            ResponseEntity.ok(mapOf("message" to "Event updated successfully", "event" to existingEvent))
        } catch (e: RuntimeException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to e.message))
        }
    }

    @CrossOrigin(origins = ["*"])
    @DeleteMapping("/{id}")
    fun deleteEvent(@PathVariable id: String): ResponseEntity<Any> {
        return if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id)
            ResponseEntity.ok(mapOf("message" to "Event deleted successfully"))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapOf("error" to "Event with id $id not found"))
        }
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