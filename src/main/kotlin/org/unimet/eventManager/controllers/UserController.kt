package org.unimet.eventManager.controllers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.UpdateUserDTO
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.models.Event
import org.unimet.eventManager.services.UserService
import java.security.Principal

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["http://localhost:5173"], allowedHeaders = ["*"], allowCredentials = "true")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/{email}/subscriptions")
    fun subscribeToEvent(
        @PathVariable email: String,
        @RequestBody eventId: Map<String, String>
    ): ResponseEntity<Any> {
        return try {
            //println("Recibiendo eventId: $eventId para el usuario: $email controlador")
            val updatedUser = userService.subscribeToEvent(email, eventId["eventId"] ?: "")
            //println("Paso el updatedUser ${updatedUser.email} controlador")
            //println("Paso el updatedUser ${updatedUser.subscribedEvents} controlador")


            //userService.clearSubscribedEvents(email)


            ResponseEntity.ok(mapOf("message" to "Successfully subscribed to the event", "user" to updatedUser))
        } catch (e: Exception) {
            //println("Entra en excepcion controlador")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to e.message))
        }
    }

    @PostMapping("/{email}/unsubscriptions")
    fun unsubscribeFromEvent(
        @PathVariable email: String,
        @RequestBody eventId: Map<String, String>
    ): ResponseEntity<Any> {
        return try {

            val updatedUser = userService.unsubscribeFromEvent(email, eventId["eventId"] ?: "")

            ResponseEntity.ok(mapOf("message" to "Successfully subscribed to the event", "user" to updatedUser))
        } catch (e: Exception) {

            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to e.message))
        }
    }

    @GetMapping("/{email}/getSubscriptions")
    fun getUserSubscriptions(
        @PathVariable email: String
    ): ResponseEntity<List<Event>> {
        return try {
            val events = userService.getUserSubscriptions(email)
            println("controlador $events")
            ResponseEntity.ok(events)
        } catch (e: Exception) {
            println("controlador fallo")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyList())
        }
    }

    @GetMapping("/getUserProfile/{email}")
    fun getUserProfile(@PathVariable email: String, principal: Principal): ResponseEntity<UserDTO> {
        if (principal.name != email) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        val user = userService.getUserProfile(email)
        return ResponseEntity.ok(user)
    }

    @PutMapping("/{email}")
    fun updateUser(
        @PathVariable email: String,
        @RequestBody updateUserDTO: UpdateUserDTO
    ): ResponseEntity<UserDTO> {
        val updatedUser = userService.updateUserProfile(email, updateUserDTO)
        return ResponseEntity.ok(updatedUser)
    }

    @GetMapping("/Events/{email}")
    fun getUserSubscribedEvents(@PathVariable email: String, principal: Principal): ResponseEntity<List<String>> {
        if (principal.name != email) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }
        val user = userService.getUserProfile(email)

        return ResponseEntity.ok(user.subscribedEvents)
    }
}