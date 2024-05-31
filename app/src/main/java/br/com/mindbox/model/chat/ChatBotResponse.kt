package br.com.mindbox.model.chat

data class ChatBotResponse(
    val message: String,
    val date: String,
    val alternatives: List<String> = listOf(),
    val expectedUserResponse: String? = null
)