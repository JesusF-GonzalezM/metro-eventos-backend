package org.unimet.eventManager.dto

data class UpdateUserDTO(
    var name: String? ?,
    var lastName: String?,
    var gender: String? = null,
    var birthDate: String? = null,
    var profilePhoto: String? = null
)
