package br.com.mindbox.service

import android.util.Log
import br.com.mindbox.dto.email.SendEmailDTO
import br.com.mindbox.model.chat.ChatBotResponse
import br.com.mindbox.model.user.User

class ChatBot(private val emailService: EmailService, private val authorizationService: AuthorizationService) {

    private val loggedUser: User = authorizationService.getLoggedUsers()[0]
    private val sendEmailDTO = createSendEmailDTO()

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
        const val EMAIL_PREVIEW = "escreva um e-mail para o gabriel dizendo para ele vir participar do next 2024, pode enviar o quanto antes"
        const val SEND_EMAIL = "perfeito, pode enviar!"
        const val UNKNOWN = "unknown"
    }

    private fun createSendEmailDTO(): SendEmailDTO {
        return SendEmailDTO(
            senderId = loggedUser.id,
            recipientIds = listOf(GABRIEL_ID),
            subject = EMAIL_SUBJECT,
            text = EMAIL_TEXT.trimIndent()
        )
    }

    fun processMessage(userMessage: String): ChatBotResponse {
        val command = userMessage.lowercase().trim()
        Log.d("ChatBot", "Received user message: $userMessage")

        return when (command) {
            ExpectedResponses.WELCOME -> getWelcomeResponse()
            ExpectedResponses.EMAIL_INSTRUCTIONS -> getEmailInstructionsResponse()
            ExpectedResponses.EMAIL_PREVIEW -> getEmailPreviewResponse()
            ExpectedResponses.SEND_EMAIL -> sendEmailAndGetConfirmationResponse()
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

    private fun getEmailInstructionsResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = """
                Ótima escolha! Vamos enviar um e-mail! ✉️
                Para facilitar, preciso que você me diga algumas coisinhas:

                1. Para quem você quer mandar o e-mail? Pode ser mais de uma pessoa, só listar os nomes ou e-mails.
                2. Qual o assunto do e-mail? Uma frase rápida sobre o que se trata.
                3. Descrição do e-mail, posso dar uma melhoradinha se quiser.
                4. Quando você quer enviar? Agora ou mais tarde?
            """.trimIndent(),
            expectedUserResponse = ExpectedResponses.EMAIL_PREVIEW
        )
    }

    private fun getEmailPreviewResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = """
                Aqui está o e-mail. Posso enviar desse jeito?
                
                Assunto: ${sendEmailDTO.subject}
                
                Para:
                $GABRIEL_EMAIL
                
                Texto:
                ${sendEmailDTO.text}
            """.trimIndent(),
            expectedUserResponse = ExpectedResponses.SEND_EMAIL
        )
    }

    private fun sendEmailAndGetConfirmationResponse(): ChatBotResponse {
        emailService.sendMail(sendEmailDTO)
        return ChatBotResponse(
            message = "Seu e-mail foi enviado com sucesso ❤️! Ajudo em algo mais?",
            expectedUserResponse = ExpectedResponses.UNKNOWN
        )
    }

    private fun getUnknownCommandResponse(): ChatBotResponse {
        return ChatBotResponse(
            message = "Desculpe, não entendi. Poderia repetir?"
        )
    }
}
