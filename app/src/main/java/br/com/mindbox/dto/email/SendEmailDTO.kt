package br.com.mindbox.dto.email

import br.com.mindbox.dto.email_task.CreateEmailTaskDTO

data class SendEmailDTO(
    val senderId: Long,
    val recipientIds: List<Long>,
    val subject: String,
    val text: String,
    val tasks: List<CreateEmailTaskDTO> = emptyList()
)