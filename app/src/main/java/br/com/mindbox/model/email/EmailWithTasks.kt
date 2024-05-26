package br.com.mindbox.model.email

import androidx.room.Embedded
import androidx.room.Relation
import br.com.mindbox.model.user.User

data class EmailWithTasks(
    @Embedded var email: Email,
    @Relation(
        parentColumn = "email_id",
        entityColumn = "email_id"
    )
    var tasks: List<EmailTask>,
    @Relation(
        parentColumn = "sender_id",
        entityColumn = "user_id"
    )
    var sender: User?
)