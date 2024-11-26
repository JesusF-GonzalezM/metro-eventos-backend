package org.unimet.eventManager.services

import org.springframework.stereotype.Service
import org.unimet.eventManager.dto.UpdateUserDTO
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.models.Event
import org.unimet.eventManager.models.User
import org.unimet.eventManager.repositories.UserRepository
import org.unimet.eventManager.repositories.EventRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val eventRepository: EventRepository
) {

    fun getUserProfile(email: String): UserDTO {
        val user = userRepository.findUserByEmail(email) ?: throw RuntimeException("Usuario no encontrado")
        return mapToDTO(user)
    }

    fun updateUserProfile(email: String, updateRequest: UpdateUserDTO): UserDTO {
        val user = userRepository.findUserByEmail(email) ?: throw RuntimeException("Usuario no encontrado")

        val updatedUser = user.apply {
            name = updateRequest.name ?: name
            lastName = updateRequest.lastName ?: lastName
            gender = updateRequest.gender ?: gender
            birthDate = updateRequest.birthDate ?: birthDate
            profilePhoto = updateRequest.profilePhoto ?: profilePhoto
        }

        val savedUser = userRepository.save(updatedUser)
        return mapToDTO(savedUser)
    }

    private fun mapToDTO(user: User): UserDTO {
        return UserDTO(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            password = "",
            role = user.role,
            gender = user.gender,
            birthDate = user.birthDate,
            profilePhoto = user.profilePhoto
        )
    }

    fun subscribeToEvent(email: String, eventId: String): User {
        //println("Recibiendo eventId: $eventId para el usuario: $email servicio")
        val user = userRepository.findUserByEmail(email) ?: throw Exception("Usuario no encontrado")
        //val aux2 = user.email
        //println("user encontrado servicio $aux2 servicio")

        /*val event = eventRepository.findEventById(eventId) ?: throw Exception("Evento no encontrado")
        val aux1 = event.id
        println("evento encontrado $aux1 servicio ")*/

        if (!user.subscribedEvents.contains(eventId)) {
            user.subscribedEvents.add(eventId)
            //val aux = user.subscribedEvents;
            //println("Recibiendo eventId: $aux servicio ")
        }
        //println("No entro en el if si no imprimio el anterior servicio")
        return userRepository.save(user)
    }

    fun getUserSubscriptions(email: String): List<Event> {
        val user = userRepository.findUserByEmail(email) ?: throw Exception("Usuario no encontrado")

        val events = mutableListOf<Event>()
        for (eventId in user.subscribedEvents) {
            val event = eventRepository.findEventById(eventId)
            if (event != null) {
                events.add(event)
            }
        }

        return events
    }


    fun clearSubscribedEvents(email: String): User {
        val user = userRepository.findUserByEmail(email) ?: throw Exception("Usuario no encontrado")

        user.subscribedEvents.clear()

        return userRepository.save(user)
    }

    fun unsubscribeFromEvent(email: String, eventId: String): User {

        val user = userRepository.findUserByEmail(email) ?: throw Exception("Usuario no encontrado")

        if (user.subscribedEvents.contains(eventId)) {

            user.subscribedEvents.remove(eventId)
        } else {
            throw Exception("El evento con id $eventId no está suscrito.")
        }
        return userRepository.save(user)
    }
}