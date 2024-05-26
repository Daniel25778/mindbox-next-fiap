package br.com.mindbox.database.repository

import android.content.Context
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.database.dao.UserDAO
import br.com.mindbox.model.user.User

class UserRepository(context: Context) {
    private val db: UserDAO = ConfigDb.getDatabase(context).userDAO()

    fun save(user: User): Long {
        return db.save(user)
    }

    fun update(user: User): Int {
        return db.update(user)
    }

    fun delete(user: User): Int {
        return db.delete(user)
    }

    fun setLoggedUser(id: Long) {
        db.setLoggedUser(id)
    }

    fun logoutAllUsers() {
        db.logoutAllUsers()
    }

    fun findLoggedUsers(): List<User> {
        return db.findLoggedUsers()
    }

    fun findUsersWithRecentEmailsSent(senderId: Long): List<User> {
        return db.findUsersWithRecentEmailsSent(senderId)
    }

    fun findAll(): List<User> {
        return db.findAll()
    }

    fun findById(id: Int): User? {
        return db.findById(id)
    }

    fun findByEmail(email: String): User? {
        return db.findByEmail(email)
    }
}