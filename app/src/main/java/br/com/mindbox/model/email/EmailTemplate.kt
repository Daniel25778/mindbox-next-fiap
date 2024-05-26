package br.com.mindbox.model.email

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_email_template")
data class EmailTemplate(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "email_template_id")
    var id: Long = 0,
    var name: String = "",
    var text: String = ""
)