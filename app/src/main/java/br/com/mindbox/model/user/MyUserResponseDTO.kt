package br.com.mindbox.model.user

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MyUserResponseDTO(
        @SerializedName("idUser")
        var id: Long = 0,
        @SerializedName("name") var fullName: String = "",
        var birthDate: Date? = null,
        var useSideBarDefaultColor: Boolean = false,
)