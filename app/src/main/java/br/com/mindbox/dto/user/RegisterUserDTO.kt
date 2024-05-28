package br.com.mindbox.dto.user

import java.util.Date

data class RegisterUserDTO(
    val email: String,
    val password: String,
    val fullName: String,
    val birthDate: Date
)
