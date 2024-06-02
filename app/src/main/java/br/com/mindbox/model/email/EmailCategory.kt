package br.com.mindbox.model.email

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_email_category")
data class EmailCategory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "email_category_id")
    var id: Long = 0,
    var name: EmailCategoryName = EmailCategoryName.EDUCATION,
    @DrawableRes var iconResIdWhite: Int = 0,
    @DrawableRes var iconResIdPurple: Int = 0
)