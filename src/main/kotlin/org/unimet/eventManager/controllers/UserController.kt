package org.unimet.eventManager.controllers
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.models.User
import org.unimet.eventManager.repositories.UserRepository

@RestController
@RequestMapping("/users")
class UserController(
    val userRepository: UserRepository
) {
    @CrossOrigin(origins = ["*"])
    @PutMapping("/{mail}")
    fun updateUser(
        @PathVariable mail: String,
        @RequestBody userDTO: UserDTO
    ) {
        val user = userRepository.findUserByEmail(mail)
        user.password = userDTO.password
        user.name = userDTO.name
        user.lastName = userDTO.lastName
        user.bookmarks = userDTO.bookmarks
        userRepository.save(user)
    }

    @CrossOrigin(origins = ["*"])
    @DeleteMapping("/{mail}")
    fun deleteUser(@PathVariable mail: String) {
        userRepository.deleteByEmail(mail)
        //TODO: Investigar si es mejor un PutMapping con el punto de borrado.
    }

    @CrossOrigin(origins = ["*"])
    @GetMapping("/{mail}") // para acceder es users/"correo del usuario"
    fun getUser(@PathVariable mail: String): User {
        return userRepository.findUserByEmail(mail)
    }
}