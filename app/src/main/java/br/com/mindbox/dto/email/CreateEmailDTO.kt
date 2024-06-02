package br.com.mindbox.dto.email

import br.com.mindbox.dto.email_task.CreateEmailTaskDTO
import br.com.mindbox.model.email.Email
import br.com.mindbox.model.email.EmailCategory

data class CreateEmailDTO(
    val email: Email,
    val emailCategory: EmailCategory,
    val recipientIds: List<Long>,
    val tasks: List<CreateEmailTaskDTO> = emptyList()
)