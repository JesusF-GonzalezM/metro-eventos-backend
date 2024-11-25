package org.unimet.eventManager.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.unimet.eventManager.models.User

interface UserRepository : MongoRepository<User, String> {
    fun findUserByEmail(email: String): User?
    fun deleteByEmail(email: String)
}