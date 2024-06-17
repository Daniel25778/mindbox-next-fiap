package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.mindbox.model.email.EmailTask

@Dao
interface EmailTaskDAO {
    @Insert
    fun insertAll(emailTasks: List<EmailTask>)


    @Query("UPDATE tbl_email_task SET is_completed = :isCompleted WHERE email_task_id = :emailTaskId")
    fun changeCompletedStatus(emailTaskId: Long, isCompleted: Boolean)
}