package org.unimet.eventManager.services

import org.springframework.stereotype.Service
import org.unimet.eventManager.dto.UpdateUserDTO
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.models.User
import org.unimet.eventManager.repositories.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository
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
}