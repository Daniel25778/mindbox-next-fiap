package br.com.mindbox.service

import android.content.Context
import br.com.mindbox.database.repository.EmailCategoryRepository
import br.com.mindbox.database.repository.EmailRepository
import br.com.mindbox.dto.email.CreateEmailDTO
import br.com.mindbox.dto.email.SendEmailDTO
import br.com.mindbox.exception.EmailCategoryNotFoundException
import br.com.mindbox.model.email.Email
import br.com.mindbox.util.email_category.EmailCategoryClassifier

class EmailService(private val context: Context) {
    private val emailRepository = EmailRepository(context)
    private val emailCategoryRepository = EmailCategoryRepository(context)
    private val emailCategoryClassifier = EmailCategoryClassifier()

    fun sendMail(sendEmailDTO: SendEmailDTO) {
        val email = Email(
            text = sendEmailDTO.text,
            subject = sendEmailDTO.subject,
            senderId = sendEmailDTO.senderId,
            sendDate = sendEmailDTO.sendDate
        )
        val emailCategoryName = emailCategoryClassifier.classifyEmail(email)
        val emailCategory = emailCategoryRepository.findByName(emailCategoryName)
            ?: throw EmailCategoryNotFoundException(emailCategoryName)

        val createEmailDTO = CreateEmailDTO(
            email = email,
            emailCategory = emailCategory,
            recipientIds = sendEmailDTO.recipientIds,
            tasks = sendEmailDTO.tasks
        )
        emailRepository.createEmailWithRelations(createEmailDTO)
    }
}