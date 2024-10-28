package org.unimet.eventManager.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.unimet.eventManager.models.User

interface UserRepository : MongoRepository<User, String> {
    @Query("{email: '?0'}")
    fun findUserByEmail(email: String): User
    @Query("{email: '?0'}", delete = true)
    fun deleteByEmail(mail: String): User
}