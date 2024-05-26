package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.mindbox.model.email.EmailTask

@Dao
interface EmailTaskDAO {
    @Insert
    fun insertAll(emailTasks: List<EmailTask>)
}