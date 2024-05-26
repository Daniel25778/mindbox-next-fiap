package br.com.mindbox.model.email

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.mindbox.model.user.User
import java.util.Date

@Entity(tableName = "tbl_email_recipient",
    foreignKeys = [
        ForeignKey(entity = Email::class, parentColumns = ["email_id"], childColumns = ["email_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["recipient_id"], onDelete = ForeignKey.CASCADE)
    ]
)
data class EmailRecipient(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "email_recipient_id")
    var id: Long = 0,
    @ColumnInfo(name = "email_id") var emailId: Long,
    @ColumnInfo(name = "email_category_id") var emailCategoryId: Long? = null,
    @ColumnInfo(name = "recipient_id") var recipientId: Long,
    @ColumnInfo(name = "finished_at") var finishedAt: Date? = null,
    @ColumnInfo(name = "marked_as_spam_at") var markedAsSpamAt: Date? = null
)