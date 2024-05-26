package br.com.mindbox.model

import com.google.gson.annotations.SerializedName

data class Advice(
    @SerializedName("slip") val slip: Slip
)

data class Slip(
    @SerializedName("id") val id: Int,
    @SerializedName("advice") val adviceDescription: String
)
