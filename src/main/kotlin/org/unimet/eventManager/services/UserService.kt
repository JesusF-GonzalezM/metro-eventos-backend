package org.unimet.eventManager.services

import org.unimet.eventManager.dto.UpdateUserDTO
import org.unimet.eventManager.dto.UserDTO
import org.unimet.eventManager.repositories.UserRepository

class UserService(
    private val userRepository: UserRepository
) {

    fun getUserProfile(email: String): UserDTO {
        val user = userRepository.findUserByEmail(email)  ?: throw RuntimeException("Usuario no encontrado")

        return UserDTO(
            email = user.email,
            name = user.name,
            lastName = user.lastName,
            gender = user.gender,
            role = user.role,
            password = user.password,
            birthDate = user.birthDate,
            profilePhoto = user.profilePhoto
        )
    }

    fun updateUserProfile(email: String, updateRequest: UpdateUserDTO): UserDTO {
        val user = userRepository.findUserByEmail(email) ?: throw RuntimeException("Usuario no encontrado")
        user.apply {
            name = updateRequest.name ?: name
            lastName = updateRequest.lastName ?: lastName
            gender = updateRequest.gender ?: gender
            birthDate = updateRequest.birthDate ?: birthDate
            profilePhoto = updateRequest.profilePhoto ?: profilePhoto
        }
        userRepository.save(user)
        return UserDTO(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            gender = user.gender,
            role = user.role,
            password = user.password,
            birthDate = user.birthDate,
            profilePhoto = user.profilePhoto
        )
    }
}