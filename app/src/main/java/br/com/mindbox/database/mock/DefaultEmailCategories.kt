package br.com.mindbox.database.mock

import br.com.mindbox.R
import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailCategoryName

class DefaultEmailCategories {
    companion object {
        fun get(): List<EmailCategory> {
            return listOf(
                EmailCategory(
                    name = EmailCategoryName.EDUCATION,
                    iconResIdPurple = R.drawable.email_category_purple_education_icon,
                    iconResIdWhite = R.drawable.email_category_white_education_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.WORK,
                    iconResIdPurple = R.drawable.email_category_purple_work_icon,
                    iconResIdWhite = R.drawable.email_category_white_work_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.PERSONAL,
                    iconResIdPurple = R.drawable.email_category_purple_person_icon,
                    iconResIdWhite = R.drawable.email_category_white_person_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.PROMOTION,
                    iconResIdPurple = R.drawable.email_category_purple_offer_icon,
                    iconResIdWhite = R.drawable.email_category_white_offer_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.FINANCE,
                    iconResIdPurple = R.drawable.email_category_purple_coin_icon,
                    iconResIdWhite = R.drawable.email_category_white_coin_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.HEALTH,
                    iconResIdPurple = R.drawable.email_category_purple_heart_icon,
                    iconResIdWhite = R.drawable.email_category_white_heart_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.TRAVEL,
                    iconResIdPurple = R.drawable.email_category_purple_plane_icon,
                    iconResIdWhite = R.drawable.email_category_white_plane_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.NEWSLETTER,
                    iconResIdPurple = R.drawable.email_category_purple_journal_icon,
                    iconResIdWhite = R.drawable.email_category_white_journal_icon
                ),
                EmailCategory(
                    name = EmailCategoryName.OTHERS,
                    iconResIdPurple = R.drawable.email_category_purple_folder_icon,
                    iconResIdWhite = R.drawable.email_category_white_folder_icon
                )
            )
        }
    }
}