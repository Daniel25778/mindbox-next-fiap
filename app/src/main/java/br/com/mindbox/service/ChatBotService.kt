package br.com.mindbox.service

import br.com.mindbox.database.repository.CalendarEventRepository
import br.com.mindbox.dto.email.SendEmailDTO
import br.com.mindbox.model.calendar.CalendarEvent
import br.com.mindbox.model.chat.ChatBotResponse
import br.com.mindbox.model.user.User
import br.com.mindbox.util.date.DateUtils
import java.util.Calendar

class ChatBotService(
    private val emailService: EmailService,
    loggedUser: User,
    private val calendarEventRepository: CalendarEventRepository
) {
    private val SEND_MAIL_INPUT_DTO = SendEmailDTO(
        senderId = loggedUser.id,
        recipientIds = listOf(GABRIEL_ID),
        subject = EMAIL_SUBJECT,
        text = EMAIL_TEXT.trimIndent(),
        sendDate = Calendar.getInstance().apply {
            add(Calendar.MINUTE, 30)
        }.time
    )


    private val SAVE_CALENDAR_EVENT_INPUT_DTO = CalendarEvent(
        title = "Brainstorming com a equipe de Marketing",
        description = "Reunião para brainstorming com todos da equipe de marketing",
        startDate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time,
        endDate = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time,
        participantsId = listOf(loggedUser.id, 4L, 5L, 6L),
        creatorId = loggedUser.id
    )

    companion object {
        private const val EMAIL_SUBJECT = "Convite para o NEXT 2024, um lugar de aprendizagem"
        private const val EMAIL_TEXT = """
            Olá, Gabriel, vim para te fazer um convite. Durante o Next 2024, você terá a oportunidade de participar de palestras estimulantes, workshops interativos e networking exclusivo. Este é o local ideal para explorar novas ideias, colaborar com colegas e impulsionar sua jornada profissional para o próximo nível.
            
            Este evento é uma oportunidade única para se conectar com os principais especialistas do setor, compartilhar insights valiosos e descobrir as últimas tendências e inovações que estão moldando o futuro.
        """
        private const val GABRIEL_EMAIL = "gabriel@locaweb.com.br"
        private const val GABRIEL_ID = 3L
    }

    private object ExpectedResponses {
        const val WELCOME = ""
        const val EMAIL_INSTRUCTIONS = "enviar e-mail"
        const val EMAIL_PREVIEW =
            "escreva um e-mail para o gabriel dizendo para ele vir participar do next 2024, envie o e-mail daqui meia hora para que eu tenha a chance de editar"
        const val SEND_EMAIL = "está tudo certo!"
        const val SCHEDULE_MEETING = "marcar reunião"
        const val MEETING_DETAILS =
            "quero me reunir com todos da equipe de marketing para um brainstorming assim que estiverem disponíveis durante o expediente"
        const val MEETING_ADJUST =
            "na verdade, a reunião irá durar uma hora"
        const val CONFIRM_MEETING = "está tudo certo"
        const val PRAISE = "Por enquanto é só. Muito obrigado!"
    }

    fun processMessage(userMessage: String): ChatBotResponse {
        return when (userMessage) {
            ExpectedResponses.WELCOME -> getWelcomeResponse()
            ExpectedResponses.PRAISE -> getPraiseResponse()
            ExpectedResponses.EMAIL_INSTRUCTIONS -> getEmailInstructionsResponse()
            ExpectedResponses.EMAIL_PREVIEW -> getEmailPreviewResponse()
            ExpectedResponses.SEND_EMAIL -> sendEmailAndGetConfirmationResponse()
            ExpectedResponses.SCHEDULE_MEETING -> getMeetingDetailsResponse()
            ExpectedResponses.MEETING_DETAILS -> getWrongMeetingToCreateResponse()
            ExpectedResponses.MEETING_ADJUST -> getAdjustedMeetingConfirmationResponse()
            ExpectedResponses.CONFIRM_MEETING -> confirmMeetingAndGetResponse()
            else -> getUnknownCommandResponse()
        }
    }


    private fun getWelcomeResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = """
                🎉 Olá, olá! Seja muito bem-vindo(a) ao nosso chat! 😄

                Quer marcar reuniões de acordo com a agenda da galera? 📅 Sem problemas!
                
                Precisa enviar uma mensagem rápida para alguém? ✉️ Tô aqui pra isso! Só me fala o que você precisa e vamos nessa! 🚀
            """.trimIndent(),
            alternatives = listOf("Enviar e-mail", "Marcar reunião", "Conferir agenda"),
            expectedUserResponse = ExpectedResponses.EMAIL_INSTRUCTIONS
        )
    }

    private fun getPraiseResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = """
                Por nada! Se precisar de mais alguma coisa, é só me chamar! 😊
            """.trimIndent(),
            alternatives = listOf("Enviar e-mail", "Marcar reunião", "Conferir agenda"),
            expectedUserResponse = ExpectedResponses.PRAISE
        )
    }

    private fun getEmailInstructionsResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = """
                Ótima escolha! Vamos enviar um e-mail! ✉️
                Para facilitar, preciso que você me diga algumas coisinhas:

                1. Para quem você quer mandar o e-mail? Pode ser mais de uma pessoa, só listar os nomes ou e-mails.
                2. Qual o assunto do e-mail? Uma frase rápida sobre o que se trata.
                3. Descrição do e-mail, posso dar uma melhoradinha se quiser.
                4. Quando você quer enviar? Agora ou mais tarde?
            """.trimIndent(), expectedUserResponse = ExpectedResponses.EMAIL_PREVIEW
        )
    }

    private fun getEmailPreviewResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = """
Aqui está o e-mail. Posso enviar desse jeito?
                
Assunto: 
${SEND_MAIL_INPUT_DTO.subject}

Para:
$GABRIEL_EMAIL

Texto:
${SEND_MAIL_INPUT_DTO.text}

Momento de enviar:
Daqui 30 miuntos
            """.trimIndent(), expectedUserResponse = ExpectedResponses.SEND_EMAIL
        )
    }

    private fun sendEmailAndGetConfirmationResponse(): ChatBotResponse {
        emailService.sendMail(SEND_MAIL_INPUT_DTO)
        return ChatBotResponse(
            message = "O envio do seu e-mail foi agendado com sucesso ❤️! Ajudo em algo mais?",
            alternatives = listOf("Enviar e-mail", "Marcar reunião", "Conferir agenda"),
            expectedUserResponse = ExpectedResponses.SCHEDULE_MEETING
        )
    }

    private fun getMeetingDetailsResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = """
            Vamos marcar uma reunião! 📅
            Por favor, forneça os seguintes detalhes:
            
            1. Data e hora da reunião.
            2. Participantes.
            3. Assunto da reunião.
        """.trimIndent(), expectedUserResponse = ExpectedResponses.MEETING_DETAILS
        )
    }

    private fun getWrongMeetingToCreateResponse(): ChatBotResponse {
        val formattedStartDate = DateUtils.getBrazilianDateTimeFormat().format(SAVE_CALENDAR_EVENT_INPUT_DTO.startDate!!)
        val participants =
            "Daniel (daniel@locaweb.com.br); Alexia (alexia@locaweb.com.br); Guilherme (gui@locaweb.com.br)"
        val subject = SAVE_CALENDAR_EVENT_INPUT_DTO.title

        return ChatBotResponse(
            message = """
        Não foi fácil encaixar um horário para todos 😅
        
        Aqui estão os detalhes da reunião que você forneceu:
        
        Data e hora de início: 
        $formattedStartDate
        
        Duração: 
        Meia hora
        
        Participantes: 
        $participants
        
        Assunto: 
        $subject
        
        Posso confirmar essa reunião? 🗓️
    """.trimIndent(), expectedUserResponse = ExpectedResponses.MEETING_ADJUST
        )
    }
    private fun getAdjustedMeetingConfirmationResponse(): ChatBotResponse {
        val formattedStartDate = DateUtils.getBrazilianDateTimeFormat().format(SAVE_CALENDAR_EVENT_INPUT_DTO.startDate!!)
        val durationDescription = DateUtils.calculateDurationDescription(
            SAVE_CALENDAR_EVENT_INPUT_DTO.startDate, SAVE_CALENDAR_EVENT_INPUT_DTO.endDate!!
        )
        val participants =
            "Daniel (daniel@locaweb.com.br); Alexia (alexia@locaweb.com.br); Guilherme (gui@locaweb.com.br)"
        val subject = SAVE_CALENDAR_EVENT_INPUT_DTO.title

        return ChatBotResponse(
            message = """
        Aqui estão os detalhes da reunião com os ajustes:
        
        Data e hora: 
        $formattedStartDate
        
        Duração: 
        $durationDescription
        
        Participantes: 
        $participants
        
        Assunto: 
        $subject
        
        Posso confirmar essa reunião? 🗓️
    """.trimIndent(), expectedUserResponse = ExpectedResponses.CONFIRM_MEETING
        )
    }

    private fun confirmMeetingAndGetResponse(): ChatBotResponse {
        this.calendarEventRepository.save(this.SAVE_CALENDAR_EVENT_INPUT_DTO)

        return ChatBotResponse(
            message = "Sua reunião foi marcada com sucesso! 🎉 Ajudo em algo mais?",
            expectedUserResponse = ExpectedResponses.PRAISE
        )
    }


    private fun getUnknownCommandResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = "Desculpe, não entendi. Poderia repetir?"
        )
    }
}
