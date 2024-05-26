package br.com.mindbox.service

import android.content.Context
import br.com.mindbox.database.repository.UserRepository
import br.com.mindbox.dto.auth.LoginDTO
import br.com.mindbox.dto.user.RegisterUserDTO
import br.com.mindbox.model.user.User
import br.com.mindbox.util.crypto.HashUtil

class AuthorizationService(private val context: Context) {
    private val userRepository = UserRepository(context)

    fun registerUser(userDTO: RegisterUserDTO): User {
        val hashedPassword = HashUtil.hashPassword(userDTO.password)
        val user = User(
            birthDate = userDTO.birthDate,
            email = userDTO.email,
            fullName = userDTO.fullName,
            passwordHash = hashedPassword,
            isLoggedIn = true
        )
        val userId = userRepository.save(user)
        return user.copy(id = userId)
    }

    fun getLoggedUsers(): List<User> {
        return userRepository.findLoggedUsers()
    }

    fun logoutAllUsers() {
        return userRepository.logoutAllUsers()
    }

    fun logoutUserById(userId: Int): User? {
        val user = userRepository.findById(userId) ?: return null
        if (!user.isLoggedIn) return null
        user.isLoggedIn = false
        userRepository.update(user)
        return user
    }

    fun authenticateUser(loginDTO: LoginDTO): User? {
        val user = userRepository.findByEmail(loginDTO.email)
        if (user == null || !HashUtil.comparePasswords(loginDTO.password, user.passwordHash)) {
            return null
        }
        userRepository.setLoggedUser(user.id)
        return user.copy(isLoggedIn = true)
    }
}