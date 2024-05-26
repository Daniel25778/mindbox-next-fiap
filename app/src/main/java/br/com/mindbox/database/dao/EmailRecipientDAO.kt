package br.com.mindbox.database.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.mindbox.model.email.EmailRecipient

@Dao
interface EmailRecipientDAO {
    @Insert
    fun insertAll(emailRecipients: List<EmailRecipient>)
}