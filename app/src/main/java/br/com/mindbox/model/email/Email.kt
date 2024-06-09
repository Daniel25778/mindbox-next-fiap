package br.com.mindbox.model.email

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mindbox.model.user.User
import java.util.Date

@Entity(tableName = "tbl_email")
data class Email(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "email_id")
    var id: Long = 0,
    @ColumnInfo(name = "sender_id") var senderId: Long,
    @Embedded var sender: User? = null,
    var subject: String = "",
    var text: String = "",
    @ColumnInfo(name = "send_date") var sendDate: Date = Date()
)