package org.unimet.eventManager.controllers
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.UpdateUserDTO
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.services.UserService
import java.security.Principal

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["http://localhost:5173"], allowedHeaders = ["*"], allowCredentials = "true")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{email}")
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

}