package br.com.mindbox.model.user;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tbl_user")
data class User(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "user_id")
        var id: Long = 0,
        var email: String = "",
        var passwordHash: String = "",
        @ColumnInfo(name = "full_name") var fullName: String = "",
        @ColumnInfo(name = "birth_date") var birthDate: Date? = null,
        @ColumnInfo(name = "profile_picture_url") var profilePictureUrl: String = "",
        @ColumnInfo(name = "is_logged_in") var isLoggedIn: Boolean = false,
        @ColumnInfo(name = "external_account_id") var externalAccountId: Long? = null
)