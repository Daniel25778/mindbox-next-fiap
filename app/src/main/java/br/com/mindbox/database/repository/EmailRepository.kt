package br.com.mindbox.database.repository

import android.content.Context
import androidx.room.Transaction
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.database.dao.EmailDAO
import br.com.mindbox.database.dao.EmailRecipientDAO
import br.com.mindbox.database.dao.EmailTaskDAO
import br.com.mindbox.dto.email.CreateEmailDTO
import br.com.mindbox.model.email.Email
import br.com.mindbox.model.email.EmailRecipient
import br.com.mindbox.model.email.EmailTask
import br.com.mindbox.model.email.EmailWithTasks

class EmailRepository(context: Context) {
    private val emailDAO: EmailDAO = ConfigDb.getDatabase(context).emailDAO()
    private val emailRecipientDAO: EmailRecipientDAO = ConfigDb.getDatabase(context).emailRecipientDAO()
    private val emailTaskDAO: EmailTaskDAO = ConfigDb.getDatabase(context).emailTaskDAO()

    @Transaction
    fun createEmailWithRelations(createEmailDTO: CreateEmailDTO) {
        val emailId = emailDAO.save(createEmailDTO.email)

        val recipients = createEmailDTO.recipientIds.map { recipientId ->
            EmailRecipient(
                emailId = emailId,
                recipientId = recipientId,
                emailCategoryId = createEmailDTO.emailCategory.id
            )
        }
        val emailTasks = createEmailDTO.tasks.map { task ->
            EmailTask(
                emailId = emailId,
                description = task.description,
                dueDate = task.dueDate,
                isCompleted = false
            )

        }
        emailRecipientDAO.insertAll(recipients)
        emailTaskDAO.insertAll(emailTasks)
    }

    fun update(email: Email): Int {
        return emailDAO.update(email)
    }

    fun delete(email: Email): Int {
        return emailDAO.delete(email)
    }

    fun findEmailsSentToUsers(recipientIds: List<Long>): List<EmailWithTasks> {
        return emailDAO.findEmailsSentToUsers(recipientIds)
    }

    fun findEmailsSentToUsersByCategory(recipientIds: List<Long>, categoryId: Long): List<EmailWithTasks> {
        return emailDAO.findEmailsSentToUsersByCategory(recipientIds, categoryId)
    }

    fun findDeletedEmailsSentToUsersByCategory(
        recipientIds: List<Long>,
        categoryId: Long
    ): List<EmailWithTasks> {
        return emailDAO.findDeletedEmailsSentToUsersByCategory(recipientIds, categoryId)
    }

    fun findDeletedEmailsSentToUsers(
        recipientIds: List<Long>,
    ): List<EmailWithTasks> {
        return emailDAO.findDeletedEmailsSentToUsers(recipientIds)
    }

    fun findSpamEmailsSentToUsers(recipientIds: List<Long>): List<EmailWithTasks> {
        return emailDAO.findSpamEmailsSentToUsers(recipientIds)
    }

    fun findSpamEmailsSentToUsersByCategory(recipientIds: List<Long>, categoryId: Long): List<EmailWithTasks> {
        return emailDAO.findSpamEmailsSentToUsersByCategory(recipientIds, categoryId)
    }
}