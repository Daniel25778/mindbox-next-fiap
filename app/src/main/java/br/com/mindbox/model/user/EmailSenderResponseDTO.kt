package br.com.mindbox.model.user

import com.google.gson.annotations.SerializedName
import java.util.Date

data class EmailSenderResponseDTO(
    @SerializedName("idUser") val idUser: Long,
    @SerializedName("name") val name: String,
    @SerializedName("birthDate") val birthDate: Date?,
    @SerializedName("role") val role: String?
)