package br.com.mindbox.service

import android.content.Context
import br.com.mindbox.dto.email.SendEmailDTO
import br.com.mindbox.model.chat.ChatBotResponse
import br.com.mindbox.model.user.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ChatBot(private val context: Context) {
    val emailService = EmailService(context)
    val loggedUser: User = AuthorizationService(context).getLoggedUsers()[0]
    val sendEmailDTO = SendEmailDTO(
        senderId = loggedUser.id,
        recipientIds = listOf(3L),
        subject = "Convite para o NEXT 2024, um lugar de aprendizagem",
        text = """
            Olá, Gabriel, vim para te fazer um convite. Durante o Next 2024, você terá a oportunidade de participar de palestras estimulantes, workshops interativos e networking exclusivo. Este é o local ideal para explorar novas ideias, colaborar com colegas e impulsionar sua jornada profissional para o próximo nível.
                
            Este evento é uma oportunidade única para se conectar com os principais especialistas do setor, compartilhar insights valiosos e descobrir as últimas tendências e inovações que estão moldando o futuro.
            """.trimIndent()
    )
    fun processMessage(userMessage: String): ChatBotResponse {
        val currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        return when (userMessage.lowercase()) {
            "" -> {
                ChatBotResponse(
                    """
                        🎉 Olá, olá! Seja muito bem-vindo(a) ao nosso chat! 😄

                        Quer marcar reuniões de acordo com a agenda da galera? 📅 Sem problemas!
                        
                        Precisa enviar uma mensagem rápida para alguém? ✉️ Tô aqui pra isso! Só me fala o que você precisa e vamos nessa! 🚀
                    """.trimIndent(),
                    currentDate,
                    listOf("Enviar e-mail", "Marcar reunião", "Conferir agenda"),
                    "Enviar e-mail"
                )
            }
            "enviar e-mail" -> {
                ChatBotResponse(
                    """
                       Ótima escolha! Vamos enviar um e-mail! ✉️
                       Para facilitar, preciso que você me diga algumas coisinhas:


                       1. Para quem você quer mandar o e-mail? Pode ser mais de uma pessoa, só listar os nomes ou e-mails.
                       2. Qual o assunto do e-mail? Uma frase rápida sobre o que se trata.
                       3. Descrição do e-mail, posso dar uma melhoradinha se quiser.
                       4. Quando você quer enviar? Agora ou mais tarde?
                    """.trimIndent(),
                    currentDate,
                    emptyList(),
                    "Escreva um e-mail para o Gabriel dizendo para ele vir participar do NEXT 2024, pode enviar o quanto antes"
                )
            }
            "Escreva um e-mail para o Gabriel dizendo para ele vir participar do NEXT 2024, pode enviar o quanto antes" -> {
                ChatBotResponse(
                    """
                        Aqui está o e-mail. Posso enviar desse jeito?
                        
                        Assunto: ${sendEmailDTO.subject}
                        
                        Para:
                        gabriel@locaweb.com.br
                        
                        Texto:
                        ${sendEmailDTO.text}
                    """.trimIndent(),
                    currentDate,
                    expectedUserResponse = "Perfeito, pode enviar!"
                )
            }
            "Perfeito, pode enviar!" -> {
                emailService.sendMail(sendEmailDTO)
                ChatBotResponse("Seu e-mail foi enviado com sucesso ❤️! Ajudo em algo mais?",
                    currentDate,
                    expectedUserResponse = ""
                )
            }
            else -> {
                ChatBotResponse(
                    "Desculpe, não entendi. Poderia repetir?",
                    currentDate
                )
            }
        }
    }
}