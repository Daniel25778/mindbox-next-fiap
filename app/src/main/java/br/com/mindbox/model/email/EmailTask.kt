package br.com.mindbox.model.email

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tbl_email_task")
data class EmailTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "email_task_id")
    var id: Long = 0,
    @ColumnInfo(name = "email_id") var emailId: Long,
    var description: String = "",
    @ColumnInfo(name = "due_date") var dueDate: Date? = null,
    @ColumnInfo(name = "is_completed") var isCompleted: Boolean = false
)