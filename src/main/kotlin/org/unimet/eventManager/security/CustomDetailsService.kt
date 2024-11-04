package org.unimet.eventManager.security

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.unimet.eventManager.repositories.UserRepository

@Service
class CustomDetailsService(
    val userRepository: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findUserByEmail(username)

        return User(user.email, user.password, listOf())
    }
}