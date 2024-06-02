package br.com.mindbox.util.email_category

import br.com.mindbox.model.email.Email
import br.com.mindbox.model.email.EmailCategoryName

class EmailCategoryClassifier {
    private val educationKeywords = listOf("educação", "escola", "universidade", "aprendizagem")
    private val workKeywords = listOf("trabalho", "emprego", "projeto", "relatório", "planejamento", "treinamento")
    private val personalKeywords = listOf("pessoal", "aniversário", "amigos", "família", "parque")
    private val promotionKeywords = listOf("promoção", "oferta", "liquidação", "desconto")
    private val financeKeywords = listOf("finanças", "dinheiro", "investimento", "financeiro", "conta corrente")
    private val healthKeywords = listOf("saúde", "médico", "médica", "consulta", "vacina", "preventivo", "fitness", "nutrição")
    private val travelKeywords = listOf("viagem", "turismo", "excursão", "praia", "montanha")
    private val shoppingKeywords = listOf("compras")
    private val newsletterKeywords = listOf("newsletter")

    fun classifyEmail(email: Email): EmailCategoryName {
        val emailContent = "${email.subject} - ${email.text}".lowercase()

        return when {
            containsAnyKeyword(emailContent, educationKeywords) -> EmailCategoryName.EDUCATION
            containsAnyKeyword(emailContent, workKeywords) -> EmailCategoryName.WORK
            containsAnyKeyword(emailContent, personalKeywords) -> EmailCategoryName.PERSONAL
            containsAnyKeyword(emailContent, promotionKeywords) -> EmailCategoryName.PROMOTION
            containsAnyKeyword(emailContent, financeKeywords) -> EmailCategoryName.FINANCE
            containsAnyKeyword(emailContent, healthKeywords) -> EmailCategoryName.HEALTH
            containsAnyKeyword(emailContent, travelKeywords) -> EmailCategoryName.TRAVEL
            containsAnyKeyword(emailContent, newsletterKeywords) -> EmailCategoryName.NEWSLETTER
            else -> EmailCategoryName.OTHERS
        }
    }
    private fun containsAnyKeyword(text: String, keywords: List<String>): Boolean {
        return keywords.any { keyword -> text.contains(keyword) }
    }
}