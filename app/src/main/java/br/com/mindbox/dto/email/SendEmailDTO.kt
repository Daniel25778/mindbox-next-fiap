package br.com.mindbox.dto.email

import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailTask

data class SendEmailDTO(
    val senderId: Long,
    val recipientIds: List<Long>,
    val subject: String,
    val text: String,
    val tasks: List<EmailTask> = emptyList()
)