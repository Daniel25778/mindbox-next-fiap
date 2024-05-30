package br.com.mindbox.model.email

enum class EmailCategoryName {
    EDUCATION,
    WORK,
    PERSONAL,
    PROMOTION,
    FINANCE,
    HEALTH,
    TRAVEL,
    NEWSLETTER,
    OTHERS
}

fun getEmailCategoryNameStrategy(categoryName: EmailCategoryName): String {
    return when (categoryName) {
        EmailCategoryName.EDUCATION -> "Educação"
        EmailCategoryName.WORK -> "Trabalho"
        EmailCategoryName.PERSONAL -> "Pessoal"
        EmailCategoryName.PROMOTION -> "Promoção"
        EmailCategoryName.FINANCE -> "Finanças"
        EmailCategoryName.HEALTH -> "Saúde"
        EmailCategoryName.TRAVEL -> "Viagem"
        EmailCategoryName.NEWSLETTER -> "Newsletter"
        EmailCategoryName.OTHERS -> "Outros"
    }
}