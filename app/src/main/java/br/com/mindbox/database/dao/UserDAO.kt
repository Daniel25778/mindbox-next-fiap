package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.mindbox.model.user.User

@Dao
interface UserDAO {
    @Insert
    fun save(user: User): Long

    @Update
    fun update(user: User): Int

    @Delete
    fun delete(user: User): Int

    @Query("UPDATE tbl_user SET is_logged_in = CASE WHEN user_id = :id THEN 1 ELSE is_logged_in END")
    fun setLoggedUser(id: Long)

    @Query("UPDATE tbl_user SET is_logged_in = 0 WHERE is_logged_in = 1")
    fun logoutAllUsers()

    @Query("SELECT * FROM tbl_user WHERE is_logged_in = 1")
    fun findLoggedUsers(): List<User>

    @Query("SELECT u.* FROM tbl_user u INNER JOIN tbl_email e ON u.user_id = e.sender_id WHERE e.sender_id = :senderId ORDER BY e.send_date DESC")
    fun findUsersWithRecentEmailsSent(senderId: Long): List<User>

    @Query("SELECT * FROM tbl_user WHERE user_id = :id")
    fun findById(id: Int): User?

    @Query("SELECT * FROM tbl_user WHERE email = :email")
    fun findByEmail(email: String): User?

    @Query("SELECT * FROM tbl_user ORDER BY full_name ASC")
    fun findAll(): List<User>
}