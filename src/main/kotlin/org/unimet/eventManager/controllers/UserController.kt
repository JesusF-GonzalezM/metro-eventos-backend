package org.unimet.eventManager.controllers
import org.springframework.web.bind.annotation.*
import org.unimet.eventManager.dto.UpdateUserDTO
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.models.User
import org.unimet.eventManager.repositories.UserRepository

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = ["http://localhost:5173"], allowedHeaders = ["*"], allowCredentials = "true")
class UserController(
    val userRepository: UserRepository
) {

    @DeleteMapping("/{mail}")
    fun deleteUser(@PathVariable mail: String) {
        userRepository.deleteByEmail(mail)
        //TODO: Investigar si es mejor un PutMapping con el punto de borrado.
    }

    @GetMapping("/{mail}") // para acceder es users/"correo del usuario"
    fun getUser(@PathVariable mail: String): User {
        return userRepository.findUserByEmail(mail)
    }

    @GetMapping("/profile/{mail}")
    @CrossOrigin(origins = ["http://localhost:5173"], allowedHeaders = ["*"])
    fun getUserProfile(@PathVariable mail: String): UserDTO {
        val user = userRepository.findUserByEmail(mail)
            ?: throw RuntimeException("Usuario no encontrado")
        return UserDTO(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            gender = user.gender,
            role = user.role,
            password = "",
            birthDate = user.birthDate,
            profilePhoto = user.profilePhoto
        )
    }

    @PutMapping("/{mail}")
    fun updateUser(
        @PathVariable mail: String,
        @RequestBody updateUserDTO: UpdateUserDTO
    ) {
        val user = userRepository.findUserByEmail(mail)
            ?: throw RuntimeException("Usuario no encontrado")

        user.name = updateUserDTO.name ?: user.name
        user.lastName = updateUserDTO.lastName ?: user.lastName
        user.gender = updateUserDTO.gender ?: user.gender
        user.birthDate = updateUserDTO.birthDate ?: user.birthDate
        user.profilePhoto = updateUserDTO.profilePhoto ?: user.profilePhoto

        userRepository.save(user)
    }


}