package br.com.mindbox.dto.user

data class RegisterUserDTO(
    val email: String,
    val password: String,
    val fullName: String,
    val birthDate: String
)
