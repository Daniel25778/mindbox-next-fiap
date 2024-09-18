package br.com.mindbox.model.email;

import br.com.mindbox.model.user.EmailSenderResponseDTO
import com.google.gson.annotations.SerializedName;
import java.util.Date

data class EmailResponseDTO(
        @SerializedName("id") val id: Long?,
        @SerializedName("sender") val sender: EmailSenderResponseDTO,
        @SerializedName("subject") val subject: String,
        @SerializedName("text") val text: String,
        @SerializedName("to") val to: String,
        @SerializedName("sendDate") val sendDate: Date
)