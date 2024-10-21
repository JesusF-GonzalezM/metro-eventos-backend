package org.unimet.eventManager

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userRepository: UserRepository) {
    @GetMapping("/users")
    fun createUser() {
        val user = User(id = "1", email="jesus@gmail.com", password="password")
        userRepository.save(user)
    }
}