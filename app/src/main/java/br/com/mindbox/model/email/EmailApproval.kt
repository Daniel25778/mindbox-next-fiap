package br.com.mindbox.model.email

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import br.com.mindbox.model.user.User
import java.util.Date

@Entity(tableName = "tbl_email_approval",
    foreignKeys = [
        ForeignKey(entity = Email::class, parentColumns = ["email_id"], childColumns = ["email_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["approver_id"], onDelete = ForeignKey.CASCADE)
    ]
)
data class EmailApproval(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "email_approval_id")
    var id: Long = 0,
    @ColumnInfo(name = "email_id") var emailId: Long,
    @ColumnInfo(name = "approver_id") var approverId: Long,
    @ColumnInfo(name = "action_date") var actionDate: Date,
    @ColumnInfo(name = "is_approved") var isApproved: Boolean // Indica se a ação foi uma aprovação
)