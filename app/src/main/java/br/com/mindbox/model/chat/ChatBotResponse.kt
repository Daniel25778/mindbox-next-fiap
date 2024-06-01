package br.com.mindbox.model.chat

import java.util.Date

data class ChatBotResponse(
    val message: String,
    val alternatives: List<String> = listOf(),
    val expectedUserResponse: String? = null,
    val date: Date = Date(),
)