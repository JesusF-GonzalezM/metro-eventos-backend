package org.unimet.eventManager.dto

data class UpdateUserDTO(
    var name: String? = null,
    var lastName: String? = null,
    var gender: String? = null,
    var birthDate: String? = null,
    var profilePhoto: String? = null
)
