package br.com.mindbox.model.externalAccount

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_external_account")
data class ExternalAccount(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "external_account_id")
    var id: Long = 0,
    var provider: ExternalAccountProvider = ExternalAccountProvider.GOOGLE,
    var email: String = "",
    var accessToken: String = ""
)