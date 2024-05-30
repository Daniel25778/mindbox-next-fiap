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
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.WORK,
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.PERSONAL,
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.PROMOTION,
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.FINANCE,
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.HEALTH,
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.TRAVEL,
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.NEWSLETTER,
                    iconResId = R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.OTHERS,
                    iconResId = R.drawable.ic_call_decline
                )
            )
        }
    }
}