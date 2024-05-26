package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailCategoryName

@Dao
interface EmailCategoryDAO {
    @Insert
    fun save(emailCategory: EmailCategory): Long

    @Update
    fun update(emailCategory: EmailCategory): Int

    @Delete
    fun delete(emailCategory: EmailCategory): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(emailCategories: List<EmailCategory>)

    @Query("SELECT * FROM tbl_email_category WHERE name = :name")
    fun findByName(name: EmailCategoryName): EmailCategory?

    @Query("SELECT * FROM tbl_email_category")
    fun findAll(): List<EmailCategory>
}