package br.com.mindbox.database.mock

import androidx.core.R
import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailCategoryName

class DefaultEmailCategories {
    companion object {
        fun get(): List<EmailCategory> {
            return listOf(
                EmailCategory(
                    name = EmailCategoryName.EDUCATION,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.WORK,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.PERSONAL,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.PROMOTION,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.FINANCE,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.HEALTH,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.TRAVEL,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.NEWSLETTER,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                ),
                EmailCategory(
                    name = EmailCategoryName.OTHERS,
                    iconResIdPurple = R.drawable.ic_call_decline,
                    iconResIdWhite = R.drawable.ic_call_decline
                )
            )
        }
    }
}