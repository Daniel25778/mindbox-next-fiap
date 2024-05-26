package br.com.mindbox.util.crypto

import java.security.MessageDigest

object HashUtil {
    private val HASH_ALGORITHM = "SHA-256"

    fun hashPassword(password: String): String {
            val digest = MessageDigest.getInstance(HASH_ALGORITHM)
            val hashBytes = digest.digest(password.toByteArray())
            return hashBytes.joinToString("") { "%02x".format(it) }
    }

    fun comparePasswords(password: String, hashedPassword: String): Boolean {
        val hashedInput = hashPassword(password)
        return hashedInput == hashedPassword
    }
}