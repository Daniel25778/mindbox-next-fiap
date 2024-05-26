package br.com.mindbox.model.email

enum class EmailCategoryName {
    EDUCATION,
    WORK,
    PERSONAL,
    PROMOTION,
    SOCIAL,
    FINANCE,
    HEALTH,
    TRAVEL,
    SHOPPING,
    NEWSLETTER,
    OTHERS
}

fun getEmailCategoryNameStrategy(categoryName: EmailCategoryName): String {
    return when (categoryName) {
        EmailCategoryName.EDUCATION -> "Educação"
        EmailCategoryName.WORK -> "Trabalho"
        EmailCategoryName.PERSONAL -> "Pessoal"
        EmailCategoryName.PROMOTION -> "Promoção"
        EmailCategoryName.SOCIAL -> "Social"
        EmailCategoryName.FINANCE -> "Finanças"
        EmailCategoryName.HEALTH -> "Saúde"
        EmailCategoryName.TRAVEL -> "Viagem"
        EmailCategoryName.SHOPPING -> "Compras"
        EmailCategoryName.NEWSLETTER -> "Newsletter"
        EmailCategoryName.OTHERS -> "Outros"
    }
}