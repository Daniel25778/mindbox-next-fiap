package br.com.mindbox.util.emailCategory

import br.com.mindbox.model.email.Email
import br.com.mindbox.model.email.EmailCategoryName

class EmailCategoryClassifier {
    private val educationKeywords = listOf("educação", "escola", "universidade")
    private val workKeywords = listOf("trabalho", "emprego")
    private val personalKeywords = listOf("pessoal")
    private val promotionKeywords = listOf("promoção", "oferta")
    private val socialKeywords = listOf("social")
    private val financeKeywords = listOf("finanças", "dinheiro")
    private val healthKeywords = listOf("saúde", "médico")
    private val travelKeywords = listOf("viagem", "turismo")
    private val shoppingKeywords = listOf("compras")
    private val newsletterKeywords = listOf("newsletter")

    fun classifyEmail(email: Email): EmailCategoryName {
        val emailContent = "${email.subject} - ${email.text}".lowercase()

        return when {
            containsAnyKeyword(emailContent, educationKeywords) -> EmailCategoryName.EDUCATION
            containsAnyKeyword(emailContent, workKeywords) -> EmailCategoryName.WORK
            containsAnyKeyword(emailContent, personalKeywords) -> EmailCategoryName.PERSONAL
            containsAnyKeyword(emailContent, promotionKeywords) -> EmailCategoryName.PROMOTION
            containsAnyKeyword(emailContent, socialKeywords) -> EmailCategoryName.SOCIAL
            containsAnyKeyword(emailContent, financeKeywords) -> EmailCategoryName.FINANCE
            containsAnyKeyword(emailContent, healthKeywords) -> EmailCategoryName.HEALTH
            containsAnyKeyword(emailContent, travelKeywords) -> EmailCategoryName.TRAVEL
            containsAnyKeyword(emailContent, shoppingKeywords) -> EmailCategoryName.SHOPPING
            containsAnyKeyword(emailContent, newsletterKeywords) -> EmailCategoryName.NEWSLETTER
            else -> EmailCategoryName.OTHERS
        }
    }
    private fun containsAnyKeyword(text: String, keywords: List<String>): Boolean {
        return keywords.any { keyword -> text.contains(keyword) }
    }
}